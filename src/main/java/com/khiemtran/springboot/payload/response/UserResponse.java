package com.khiemtran.springboot.payload.response;

public record UserResponse(
    String firstName,
    String lastName,
    int age,
    String email
) {
}
