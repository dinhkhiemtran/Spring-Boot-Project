package com.khiemtran.springboot.service.impl;

import com.khiemtran.springboot.payload.request.UserRequest;
import com.khiemtran.springboot.payload.response.UserResponse;
import com.khiemtran.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
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
  public UserResponse save(UserRequest userRequest) throws BadRequestException {
    if (!database.containsKey(userRequest.id())) {
      database.put(userRequest.id(), userRequest);
      return new UserResponse(userRequest.firstName(), userRequest.lastName(), userRequest.age(), userRequest.email());
    } else {
      throw new BadRequestException("User already exists.");
    }
  }

  @Override
  public List<UserResponse> getAllUser() {
    return database.values().stream()
        .map(UserRequest::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public UserResponse getUser(long id) throws BadRequestException {
    if (database.containsKey(id)) {
      return database.get(id).toResponse();
    } else {
      throw new BadRequestException("User is no exists.");
    }
  }

  @Override
  public void editUser(long id, UserRequest sanitizer) throws BadRequestException {
    if (database.containsKey(id)) {
      UserRequest userRequest = database.get(id);
      userRequest = new UserRequest(sanitizer.id(),
          sanitizer.firstName(),
          sanitizer.lastName(),
          sanitizer.age(),
          sanitizer.city(),
          sanitizer.email());
      database.put(id, userRequest);
    } else {
      throw new BadRequestException("User is no exists.");
    }
  }

  @Override
  public void deleteUser(long id) throws BadRequestException {
    if (database.containsKey(id)) {
      database.remove(id);
    } else {
      throw new BadRequestException("User is not exists");
    }
  }
}
