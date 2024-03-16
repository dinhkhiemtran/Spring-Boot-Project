package com.khiemtran.springboot.payload.response;

public record UserResponse(
    String name,
    String username,
    String email
) {
}
