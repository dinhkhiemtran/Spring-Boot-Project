package com.khiemtran.springboot.controller;

import com.khiemtran.springboot.payload.request.UserRequest;
import com.khiemtran.springboot.payload.response.UserResponse;
import com.khiemtran.springboot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/user")
  public ResponseEntity<?> save(@Valid @RequestBody UserRequest userRequest) {
    UserRequest sanitizer = userRequest.sanitize(userRequest);
    UserResponse userResponse = userService.save(sanitizer);
    URI location = ServletUriComponentsBuilder
        .fromCurrentContextPath().path("/api/users/{email}")
        .buildAndExpand(userResponse.email()).toUri();
    return ResponseEntity.created(location).body("User registered successfully");
  }
}
