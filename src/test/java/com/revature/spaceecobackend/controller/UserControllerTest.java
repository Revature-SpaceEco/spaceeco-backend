package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.dto.RegisterUserDTO;
import com.revature.spaceecobackend.dto.UserDTO;
import com.revature.spaceecobackend.model.Address;
import com.revature.spaceecobackend.model.BillingDetails;
import com.revature.spaceecobackend.model.User;
import com.revature.spaceecobackend.model.UserRole;
import com.revature.spaceecobackend.service.MfaService;
import com.revature.spaceecobackend.service.UserService;
import dev.samstevens.totp.exceptions.QrGenerationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.registerCustomDateFormat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserService userService;

    @Mock
    private MfaService mfaService;

    @InjectMocks
    UserController userController;

    private static UserDTO userDTO;
    private static UserRole role;
    private static Address address;
    private static BillingDetails billingDetails;
    private static User user;
    private static RegisterUserDTO registerUserDTO;
    private static User user2;
    private static List<User> userList;
    @BeforeAll
    public static void init() {

        role = new UserRole(1, "admin");
        address = new Address(1, "1 something street", "TestYoyo city", "TestCity", "TestState", "TestCountry",
                "8823", "Test", "TestPlanet");
        billingDetails = new BillingDetails();

        ModelMapper modelMapper = new ModelMapper();
        user = new User(0, "test", "sadsa", "test@email", "test", "test@test.com", role, address, billingDetails, "Person Profile", true, "secret");
        registerUserDTO = modelMapper.map(user, RegisterUserDTO.class);
        userDTO = new UserDTO(0,"test","test@email", "test", "test@test.com", role, address, billingDetails, "Person Profile", "qrCode");
        user2 = new User(0, "test", "sadsa", "test@email", "test", "test@test.com", role, address, billingDetails, "Person Profile", true, "secret");

        userList = new ArrayList<User>();
        userList.add(user);
        userList.add(user2);

    }

    @Test
    void createUser_positive() throws QrGenerationException {
        ModelMapper modelMapper = new ModelMapper();
        User modeledUser = modelMapper.map(registerUserDTO, User.class);
        modeledUser.setSecret(user.getSecret());
        modeledUser.setActive(user.isActive());

        when(passwordEncoder.encode(user.getPassword())).thenReturn(user.getPassword());
        when(userService.createUser(modeledUser)).thenReturn(userDTO);
        when(mfaService.getSecret()).thenReturn(user.getSecret());
        when(mfaService.getQrCode(user.getSecret(), user.getEmail())).thenReturn(userDTO.getQrCode());

        ResponseEntity<?> expected = ResponseEntity.status(200).body(userDTO);
        ResponseEntity<?> actual = userController.AddUser(registerUserDTO);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void test_getAllusers(){

        when(userService.getAllUsers()).thenReturn(userList);
        ResponseEntity<?> response = userController.getAllUsers();
        assertThat(response.getBody()).isEqualTo(userList);
    }

}