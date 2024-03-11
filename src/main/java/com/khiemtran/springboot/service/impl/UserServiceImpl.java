package com.khiemtran.springboot.service.impl;

import com.khiemtran.springboot.payload.request.UserRequest;
import com.khiemtran.springboot.payload.response.UserResponse;
import com.khiemtran.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
  private static final Map<Long, UserRequest> database = new HashMap<>();

  @Override
  public UserResponse save(UserRequest userRequest) {
    if (!database.containsKey(userRequest.id())) {
      database.put(userRequest.id(), userRequest);
      return new UserResponse(userRequest.firstName(), userRequest.lastName(), userRequest.age(), userRequest.email());
    } else {
      throw new IllegalArgumentException("The user already exists.");
    }
  }

  @Override
  public List<UserResponse> getAllUser() {
    return database.values().stream()
        .map(UserRequest::toResponse)
        .collect(Collectors.toList());
  }
}
