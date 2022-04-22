package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.UserRepository;
import com.revature.spaceecobackend.model.Address;
import com.revature.spaceecobackend.model.BillingDetails;
import com.revature.spaceecobackend.model.User;
import com.revature.spaceecobackend.model.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {


    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    public void getAllUsersTest_positive() {
        List<User> userList = userService.getAllUsers();
        assertThat(userList).isNotNull();
    }


    @Test
    public void createUser_positive(){
        UserRole role = new UserRole(1, "admin");
        Address address = new Address(1, "1 something street", "TestYoyo city", "TestCity", "TestState", "TestCountry",
                "8823", "Test", "TestPlanet");
        BillingDetails billingDetails = new BillingDetails();

        User user = new User(0, "test", "password", "test", "test", "test@test.com", role, address, billingDetails, "Person Profile",true);
        when(userRepository.saveAndFlush(user)).thenReturn(user);
        User actual = userService.createUser(user);
        assertThat(actual).isEqualTo(user);
    }


}