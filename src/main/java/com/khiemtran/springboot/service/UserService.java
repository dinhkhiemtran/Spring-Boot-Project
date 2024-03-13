package com.khiemtran.springboot.service;

import com.khiemtran.springboot.payload.request.UserRequest;
import com.khiemtran.springboot.payload.response.UserResponse;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface UserService {
  UserResponse save(UserRequest userRequest) throws BadRequestException;

  List<UserResponse> getAllUser();

  UserResponse getUser(long id) throws BadRequestException;

  void editUser(long id, UserRequest sanitizer) throws BadRequestException;

  void deleteUser(long id) throws BadRequestException;
}
