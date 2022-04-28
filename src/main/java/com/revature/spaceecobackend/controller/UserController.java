package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.dto.RegisterUserDTO;
import com.revature.spaceecobackend.dto.UserDTO;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.model.User;
import com.revature.spaceecobackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
@RequestMapping("/users")
public class UserController {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserService userService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping()
  public ResponseEntity<?> AddUser(@RequestBody RegisterUserDTO registerUserDTO) {
    registerUserDTO.setActive(true);
    registerUserDTO.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));

    boolean result = userService.createUser(registerUserDTO);

    if (result) {
      return ResponseEntity.status(200).body("User created successfully.");
    } else {
      return ResponseEntity.status(400).body("Username or email already exist.");
    }
  }

  @GetMapping()
  public ResponseEntity<?> getAllUsers() {
    List<User> users = userService.getAllUsers();
    return ResponseEntity.ok().body(users);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<?> getUserById(@PathVariable("userId") int id) {
    try {
      UserDTO userDTO = userService.getUserById(id);
      return ResponseEntity.ok().body(userDTO);
    } catch (NotFound e) {
      return ResponseEntity.notFound().build();
    }
  }

}
