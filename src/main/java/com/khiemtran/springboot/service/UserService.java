package com.khiemtran.springboot.service;

import com.khiemtran.springboot.payload.request.UserRequest;
import com.khiemtran.springboot.payload.response.UserResponse;

public interface UserService {
  UserResponse save(UserRequest userRequest);
}
