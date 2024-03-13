package com.khiemtran.springboot.controller;

import com.khiemtran.springboot.payload.request.UserRequest;
import com.khiemtran.springboot.payload.response.UserResponse;
import com.khiemtran.springboot.service.UserService;
import com.khiemtran.springboot.utils.SanitizerUtils;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/user")
  public ResponseEntity<?> save(@Valid @RequestBody UserRequest userRequest) throws BadRequestException {
    UserRequest sanitizer = userRequest.sanitize(userRequest);
    UserResponse userResponse = userService.save(sanitizer);
    URI location = ServletUriComponentsBuilder
        .fromCurrentContextPath().path("/api/users/{email}")
        .buildAndExpand(userResponse.email()).toUri();
    return ResponseEntity.created(location).body("User registered successfully");
  }

  @GetMapping("/users")
  public ResponseEntity<List<UserResponse>> getAllUsers() {
    List<UserResponse> response = userService.getAllUser();
    return ResponseEntity.ok().body(response);
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<UserResponse> getUser(@PathVariable long id) throws BadRequestException {
    UserResponse userResponse = userService.getUser(SanitizerUtils.sanitizeLong(id));
    return ResponseEntity.ok().body(userResponse);
  }

  @PutMapping("/user/{id}")
  public ResponseEntity<?> editUser(@PathVariable long id, @RequestBody UserRequest userRequest) throws BadRequestException {
    UserRequest sanitizer = userRequest.sanitize(userRequest);
    userService.editUser(id, sanitizer);
    return ResponseEntity.ok().body("User edited successfully");
  }

  @DeleteMapping("/user/{id}")
  public ResponseEntity<String> delete(@PathVariable long id) throws BadRequestException {
    userService.deleteUser(id);
    return ResponseEntity.ok().body("User deleted successfully");
  }
}
