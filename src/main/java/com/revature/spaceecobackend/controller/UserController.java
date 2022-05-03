package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.dto.RegisterUserDTO;
import com.revature.spaceecobackend.dto.UserDTO;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.model.User;
import com.revature.spaceecobackend.service.MfaService;
import com.revature.spaceecobackend.service.UserService;
import dev.samstevens.totp.exceptions.QrGenerationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserService userService;

  @Autowired
  private MfaService mfaService;

  @PostMapping()
  public ResponseEntity<?> addUser(@RequestBody RegisterUserDTO registerUserDTO) throws QrGenerationException, NotFound {

    try{
      ModelMapper modelMapper = new ModelMapper();
      User user = modelMapper.map(registerUserDTO, User.class);

      user.setActive(true);
      user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
      user.setSecret(mfaService.getSecret());
      String qrCode = mfaService.getQrCode(user.getSecret(), user.getEmail());

      UserDTO returnUser = userService.createUser(user);

      returnUser.setQrCode(qrCode);

      return ResponseEntity.status(200).body(returnUser);


    }catch (NotFound e){
      return ResponseEntity.status(400).body(e.getMessage());
    }
//    ModelMapper modelMapper = new ModelMapper();
//    User user = modelMapper.map(registerUserDTO, User.class);
//
//    user.setActive(true);
//    user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
//    user.setSecret(mfaService.getSecret());
//    String qrCode = mfaService.getQrCode(user.getSecret(), user.getEmail());
//
//    UserDTO returnUser = userService.createUser(user);
//
//    if (returnUser != null) {
//        returnUser.setQrCode(qrCode);
//
//        return ResponseEntity.status(200).body(returnUser);
//    } else {
//        return ResponseEntity.status(400).body("Registration Failed");
//    }
  }

  @GetMapping()
  public ResponseEntity<?> getAllUsers() {
    List<User> users = userService.getAllUsers();
    return ResponseEntity.ok().body(users);
  }



}
