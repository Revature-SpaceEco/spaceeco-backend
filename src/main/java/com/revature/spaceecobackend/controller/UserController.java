package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.dto.UserDTO;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.model.User;
import com.revature.spaceecobackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping()
  public UserDTO AddUser(@RequestBody User user) {
    user.setActive(true);
    User rtnUser = userService.createUser(user);

    if (rtnUser != null) {
      return modelMapper.map(rtnUser, UserDTO.class);
    }

    return null;
  }

  @GetMapping()
  public ResponseEntity<?> getAllUsers() {
    List<User> users = userService.getAllUsers();
    return ResponseEntity.ok().body(users);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
    try {
      UserDTO userDTO = userService.getUserById(id);
      return ResponseEntity.ok().body(userDTO);
    } catch (NotFound e) {
      return ResponseEntity.notFound().build();
    }
  }

}
