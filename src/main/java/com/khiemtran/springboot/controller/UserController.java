package com.khiemtran.springboot.controller;

import com.khiemtran.springboot.payload.request.UserRequest;
import com.khiemtran.springboot.service.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
