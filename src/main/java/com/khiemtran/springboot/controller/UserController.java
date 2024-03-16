package com.khiemtran.springboot.controller;

import com.khiemtran.springboot.payload.request.UserRequest;
import com.khiemtran.springboot.payload.response.UserResponse;
import com.khiemtran.springboot.service.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/user")
  public ResponseEntity<?> save(@RequestBody @Valid UserRequest userRequest) throws BadRequestException {
    UserRequest sanitizer = userRequest.sanitize(userRequest);
    userService.save(sanitizer);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/users")
  public ResponseEntity<List<UserResponse>> getAllUsers() throws BadRequestException {
    List<UserResponse> users = userService.getAllUser();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/users/count")
  public ResponseEntity<String> countUsers() {
    String response = String.format("Total Users: %s", userService.count());
    return ResponseEntity.ok(response);
  }

  @GetMapping("users/name")
  public ResponseEntity<?> getAllNamesUsers() throws BadRequestException {
    List<String> names = userService.getNames();
    return ResponseEntity.ok(names);
  }
}
