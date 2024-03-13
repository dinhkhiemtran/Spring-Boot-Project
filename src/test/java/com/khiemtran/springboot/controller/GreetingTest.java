package com.khiemtran.springboot.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = Greeting.class)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class GreetingTest {
  @InjectMocks
  private Greeting greeting;
  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(greeting).build();
  }

  @Test
  public void testGreeting() throws Exception {
    mockMvc.perform(get("/greeting"))
        .andExpect(status().isOk())
        .andExpect(content().string("Hello Spring Boot."));
  }
}