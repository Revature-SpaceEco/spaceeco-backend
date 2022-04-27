package com.revature.spaceecobackend.dao;

import com.revature.spaceecobackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  // Used in Spring Security
  Optional<User> findByusername(String userName);
}
