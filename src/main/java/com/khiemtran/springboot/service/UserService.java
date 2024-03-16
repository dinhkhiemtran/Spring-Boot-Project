package com.khiemtran.springboot.service;

import com.khiemtran.springboot.payload.request.UserRequest;
import com.khiemtran.springboot.payload.response.UserResponse;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface UserService {
  void save(UserRequest userRequest) throws BadRequestException;

  List<UserResponse> getAllUser() throws BadRequestException;

  long count();

  List<String> getNames() throws BadRequestException;
}
