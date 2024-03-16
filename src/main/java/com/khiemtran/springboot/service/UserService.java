package com.khiemtran.springboot.service;

import com.khiemtran.springboot.payload.request.UserRequest;
import com.khiemtran.springboot.payload.response.UserResponse;
import org.apache.coyote.BadRequestException;

public interface UserService {
  void save(UserRequest userRequest) throws BadRequestException;
}
