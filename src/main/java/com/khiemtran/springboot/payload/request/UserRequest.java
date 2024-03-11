package com.khiemtran.springboot.payload.request;

import com.khiemtran.springboot.payload.response.UserResponse;
import com.khiemtran.springboot.security.Sanitizer;
import com.khiemtran.springboot.utils.SanitizerUtils;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
    @NotNull(message = "ID field must not be null.") long id,
    @NotNull(message = "First name field must not be null.") String firstName,
    @NotNull(message = "Last name field must not be null.") String lastName,
    int age,
    String city,
    @NotNull(message = "Email field must not be null.") @Email(message = "Email invalid") String email
) implements Sanitizer<UserRequest> {
  @Override
  public UserRequest sanitize(UserRequest userRequest) {
    return new UserRequest(
        SanitizerUtils.sanitizeLong(userRequest.id),
        SanitizerUtils.sanitizeString(userRequest.firstName),
        SanitizerUtils.sanitizeString(userRequest.lastName),
        SanitizerUtils.sanitizeInt(age),
        SanitizerUtils.sanitizeString(city),
        SanitizerUtils.sanitizeString(email));
  }

  public UserResponse toResponse() {
    return new UserResponse(firstName, lastName, age, email);
  }
}
