package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.dto.UserDTO;
import com.revature.spaceecobackend.model.User;
import com.revature.spaceecobackend.service.MfaService;
import com.revature.spaceecobackend.service.UserService;
import dev.samstevens.totp.exceptions.QrGenerationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
  private MfaService mfaService;

  @PostMapping()
  public ResponseEntity<?> AddUser(@RequestBody User user) throws QrGenerationException {
    user.setActive(true);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    String secret = mfaService.getSecret();
    user.setSecret(secret);
    String qrCode = mfaService.getQrCode(secret, user.getEmail());

    User rtnUser = userService.createUser(user);

    // TODO create mapper to clean this up
    if (rtnUser != null) {
      UserDTO userDTO = new UserDTO();
      userDTO.setId(rtnUser.getId());
      userDTO.setUsername(rtnUser.getUsername());
      userDTO.setFirstName(rtnUser.getFirstName());
      userDTO.setLastName(rtnUser.getLastName());
      userDTO.setEmail(rtnUser.getEmail());
      userDTO.setImageUrl(rtnUser.getImageUrl());
      userDTO.setUserRole(rtnUser.getUserRole());
      userDTO.setPrimaryAddress(rtnUser.getPrimaryAddressId());
      userDTO.setPrimaryBilling(rtnUser.getPrimaryBillingId());
      userDTO.setActive(rtnUser.isActive());
      return ResponseEntity.ok(new Object[]{userDTO, qrCode});
    }
    return ResponseEntity.status(400).body("Registration Failed");
  }

  @GetMapping()
  public ResponseEntity<?> getAllUsers() {
    List<User> users = userService.getAllUsers();
    return ResponseEntity.ok().body(users);
  }



}