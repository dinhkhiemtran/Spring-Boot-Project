package com.khiemtran.springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greeting {
  @GetMapping("/greeting")
  public ResponseEntity greeting() {
    return ResponseEntity.ok("Hello Spring Boot.");
  }
}
