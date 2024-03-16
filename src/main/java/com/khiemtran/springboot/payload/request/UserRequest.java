package com.khiemtran.springboot.payload.request;

import com.khiemtran.springboot.model.User;
import com.khiemtran.springboot.security.Sanitizer;
import com.khiemtran.springboot.utils.SanitizerUtils;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
    String name,
    @NotNull(message = "Username is not null") String username,
    @NotNull(message = "Email is not null") @Email String email,
    @NotNull(message = "Password is not null") String password
) implements Sanitizer<UserRequest> {
  @Override
  public UserRequest sanitize(UserRequest userRequest) {
    return new UserRequest(SanitizerUtils.sanitizeString(userRequest.name),
        SanitizerUtils.sanitizeString(userRequest.username),
        SanitizerUtils.sanitizeString(email),
        SanitizerUtils.sanitizeString(password));
  }

  public User toEntity() {
    return new User(name, username, email, password);
  }
}
