package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.dto.UserDTO;
import com.revature.spaceecobackend.model.User;
import com.revature.spaceecobackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*",exposedHeaders = "*",allowedHeaders = "*")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public UserDTO AddUser(@RequestBody User user)
    {
        User rtnUser = userService.createUser(user);

        if(rtnUser != null) {
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
            return userDTO;
        }
        return null;
    }

    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

}
