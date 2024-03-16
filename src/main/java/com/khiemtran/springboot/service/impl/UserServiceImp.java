package com.khiemtran.springboot.service.impl;

import com.khiemtran.springboot.model.User;
import com.khiemtran.springboot.payload.request.UserRequest;
import com.khiemtran.springboot.payload.response.UserResponse;
import com.khiemtran.springboot.repository.UserRepository;
import com.khiemtran.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImp implements UserService {
  private UserRepository userRepository;

  public UserServiceImp(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void save(UserRequest userRequest) throws BadRequestException {
    User user = userRequest.toEntity();
    if (userRepository.existsByEmail(user.getEmail())) {
      log.error("User is already exist");
      throw new BadRequestException("User is already exist");
    } else {
      userRepository.save(user);
      log.info("User saved successfully");
    }
  }

  @Override
  public List<UserResponse> getAllUser() throws BadRequestException {
    List<User> users = Optional.ofNullable(userRepository.findAll())
        .orElseThrow(() -> new BadRequestException("Empty."));
    return users.stream().map(user -> user.toResponse()).toList();
  }

  @Override
  public long count() {
    return userRepository.count();
  }

  @Override
  public List<String> getNames() throws BadRequestException {
    var users = Optional.ofNullable(userRepository.findAllNames())
        .orElseThrow(() -> new BadRequestException("Empty."));
    return users.stream().toList();
  }
}
