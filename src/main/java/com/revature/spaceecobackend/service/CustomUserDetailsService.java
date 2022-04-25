package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.UserRepository;
import com.revature.spaceecobackend.model.CustomUserDetails;
import com.revature.spaceecobackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByusername(userName);

    user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

    return user.map(CustomUserDetails::new).get();

  }

}
