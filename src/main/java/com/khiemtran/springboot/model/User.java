package com.khiemtran.springboot.model;

import com.khiemtran.springboot.payload.response.UserResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "username"
    }),
    @UniqueConstraint(columnNames = {
        "email"
    })
})
@NoArgsConstructor
@Data
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Size(max = 40)
  private String name;
  @NotBlank
  @Size(max = 15)
  private String username;
  @NotBlank
  @Size(max = 40)
  @NaturalId
  private String email;
  @NotBlank
  @Size(max = 100)
  private String password;

  public User(String name, String username, String email, String password) {
    this.name = name;
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public UserResponse toResponse() {
    return new UserResponse(name, username, email);
  }
}
