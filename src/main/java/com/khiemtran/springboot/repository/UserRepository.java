package com.khiemtran.springboot.repository;

import com.khiemtran.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByEmail(String email);

  @Query(
      value = "SELECT name " +
          "FROM USERS",
      nativeQuery = true)
  List<String> findAllNames();
}
