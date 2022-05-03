package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.UserRepository;
import com.revature.spaceecobackend.model.CustomUserDetails;
import com.revature.spaceecobackend.model.User;
import com.revature.spaceecobackend.model.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService userDetailsService;

    @Test
    void positiveTest_loadUserByUsername() {
        UserRole userRole = new UserRole();
        userRole.setId(1);
        userRole.setRole("ROLE_BUYER");

        User user = new User();
        user.setUsername("Joe");
        user.setPassword("Schmimmyshmam");
        user.setUserRole(userRole);
        user.setActive(true);

        when(userRepository.findByusername("Joe")).thenReturn(Optional.of(user));

        UserDetails expected = new CustomUserDetails(user);
        UserDetails actual = userDetailsService.loadUserByUsername("Joe");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void negativeTest_loadUserByUsername() {

        when(userRepository.findByusername("")).thenReturn(Optional.empty());

        Assertions.assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(""));


    }
}
