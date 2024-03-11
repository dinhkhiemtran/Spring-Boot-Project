package com.khiemtran.springboot.service;

import com.khiemtran.springboot.payload.request.UserRequest;
import com.khiemtran.springboot.payload.response.UserResponse;

import java.util.List;

public interface UserService {
  UserResponse save(UserRequest userRequest);

  List<UserResponse> getAllUser();
}
