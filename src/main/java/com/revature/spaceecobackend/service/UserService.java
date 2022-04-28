package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.UserRepository;
import com.revature.spaceecobackend.dto.UserDTO;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Transactional
  public User createUser(User user) {
    return userRepository.save(user);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public UserDTO getUserById(int id) throws NotFound {
    Optional<User> optional = userRepository.findById(id);

    if(optional.isPresent()) {
      return modelMapper.map(optional.get(), UserDTO.class);
    } else {
      System.out.println(optional);
      throw new NotFound("The user with the id " + id + " does not exist.");
    }
  }

}
