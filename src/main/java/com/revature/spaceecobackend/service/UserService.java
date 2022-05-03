package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.UserRepository;
import com.revature.spaceecobackend.dto.UserDTO;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  public UserDTO createUser(User user) throws NotFound {
    // get the user from the database if exist
    User dbUser = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());

    if(dbUser != null) {
      throw new NotFound("Registration Failed");
    }
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(userRepository.save(user), UserDTO.class);

//    ModelMapper modelMapper = new ModelMapper();
//    // if the user not already exist
//    // then we create and return the user
//    if(dbUser == null) {
//      return modelMapper.map(userRepository.save(user), UserDTO.class);
//    } else {
//      return null;
//    }
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public UserDTO getUserById(int id) throws NotFound {
    Optional<User> optional = userRepository.findById(id);

    ModelMapper modelMapper = new ModelMapper();
    if(optional.isPresent()) {
      return modelMapper.map(optional.get(), UserDTO.class);
    } else {
      throw new NotFound("The user with the id " + id + " does not exist.");
    }
  }

}
