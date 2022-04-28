package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.dao.UserRepository;
import com.revature.spaceecobackend.dto.UserDTO;
import com.revature.spaceecobackend.exception.EmptyFields;
import com.revature.spaceecobackend.model.Address;
import com.revature.spaceecobackend.model.BillingDetails;
import com.revature.spaceecobackend.model.User;
import com.revature.spaceecobackend.model.UserRole;
import com.revature.spaceecobackend.service.UserService;
import dev.samstevens.totp.exceptions.QrGenerationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserService userService;

    @InjectMocks
    UserController userController;

    private static UserDTO userDTO;
    private static UserRole role;
    private static Address address;
    private static BillingDetails billingDetails;
    private static User user;
    private static User user2;
    private static List<User> userList;
    @BeforeAll
    public static void init() {


        role = new UserRole(1, "admin");
        address = new Address(1, "1 something street", "TestYoyo city", "TestCity", "TestState", "TestCountry",
                "8823", "Test", "TestPlanet");
        billingDetails = new BillingDetails();

        user = new User(0, "test", "sadsa", "test@email", "test", "test@test.com", role, address, billingDetails, "Person Profile", true, "secret");
        userDTO = new UserDTO(0,"test","test@email", "test", "test@test.com", role, address, billingDetails, "Person Profile", true);
        user2 = new User(0, "test", "sadsa", "test@email", "test", "test@test.com", role, address, billingDetails, "Person Profile", true, "secret");

        userList = new ArrayList<User>();
        userList.add(user);
        userList.add(user2);


    }

    @Test
    void createUser_positive() throws QrGenerationException {
        when(passwordEncoder.encode(user.getPassword())).thenReturn(user.getPassword());
        when(userService.createUser(user)).thenReturn(user);
        ResponseEntity<?> response = userController.AddUser(user);
        assertThat(response).isEqualTo(userDTO);
    }

    @Test
    void test_getAllusers(){

        when(userService.getAllUsers()).thenReturn(userList);
        ResponseEntity<?> response = userController.getAllUsers();
        assertThat(response.getBody()).isEqualTo(userList);
    }

}