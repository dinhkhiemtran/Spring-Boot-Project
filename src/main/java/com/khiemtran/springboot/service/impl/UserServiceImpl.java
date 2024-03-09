package com.khiemtran.springboot.service.impl;

import com.khiemtran.springboot.payload.request.UserRequest;
import com.khiemtran.springboot.payload.response.UserResponse;
import com.khiemtran.springboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
  private Map<Long, UserRequest> map = new HashMap<>();

  @Override
  public UserResponse save(UserRequest userRequest) {
    if (!map.containsKey(userRequest.id())) {
      map.put(userRequest.id(), userRequest);
      return new UserResponse(userRequest.firstName(), userRequest.lastName(), userRequest.age(), userRequest.email());
    } else {
      throw new IllegalArgumentException("The user already exists.");
    }
  }
}
