package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.model.User;
import com.revature.spaceecobackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {

    @Autowired
     UserService userService;
    @PostMapping("/user")
    public User AddUser(@RequestBody User user)
    {
        return userService.createUser(user);

    }

}
