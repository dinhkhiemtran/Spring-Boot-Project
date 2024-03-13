package com.khiemtran.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khiemtran.springboot.payload.request.UserRequest;
import com.khiemtran.springboot.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = UserController.class)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class UserControllerTest {
  @InjectMocks
  private UserController userController;
  @MockBean
  private UserService userService;
  private MockMvc mockMvc;
  private UserRequest userRequest;

  @BeforeEach
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    ReflectionTestUtils.setField(userController, "userService", userService);
    userRequest = new UserRequest(1l, "test", "test", 12, "test", "test@mail.com");
  }

  @Test
  public void tesSaveUser() throws Exception {
    when(userService.save(any())).thenReturn(userRequest.toResponse());
    mockMvc.perform(post("/api/v1/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(userRequest)))
        .andExpect(status().isCreated())
        .andExpect(content().string("User registered successfully"))
        .andExpect(header().stringValues("Location", "http://localhost/api/users/test@mail.com"));
  }

  @Test
  public void testGetAllUser() throws Exception {
    UserRequest userRequest1 = new UserRequest(1, "test1", "test1", 11, "test1", "test1@mail.com");
    UserRequest userRequest2 = new UserRequest(2, "test2", "test2", 22, "test2", "test2@mail.com");
    when(userService.getAllUser()).thenReturn(List.of(
        userRequest1.toResponse(),
        userRequest2.toResponse()));
    mockMvc.perform(get("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(new ObjectMapper().writeValueAsString(List.of(userRequest1.toResponse(), userRequest2.toResponse()))));
  }

  @Test
  public void testGetUser() throws Exception {
    when(userService.getUser(anyLong())).thenReturn(userRequest.toResponse());
    mockMvc.perform(get("/api/v1/user/{id}", 1l)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(new ObjectMapper().writeValueAsString(userRequest.toResponse())));
  }

  @Test
  public void testEditUser() throws Exception {
    doNothing().when(userService).editUser(anyLong(), any());
    mockMvc.perform(put("/api/v1/user/{id}", 1l)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(userRequest)))
        .andExpect(status().isOk())
        .andExpect(content().string("User edited successfully"));
  }

  @Test
  public void testDeleteUser() throws Exception {
    doNothing().when(userService).deleteUser(anyLong());
    mockMvc.perform(delete("/api/v1/user/{id}", 1l)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().string("User deleted successfully"));
  }
}