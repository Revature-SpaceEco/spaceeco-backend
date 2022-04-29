package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.model.*;
import com.revature.spaceecobackend.service.CustomUserDetailsService;
import com.revature.spaceecobackend.service.MfaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private CustomUserDetailsService userDetailsService;

    @Mock
    private MfaService mfaService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @Test
    void positiveTest_createAuthenticationToken() throws Exception {

        UserRole role = new UserRole(1, "admin");
        Address address = new Address(1, "1 something street", "TestYoyo city", "TestCity", "TestState", "TestCountry",
                "8823", "Test", "TestPlanet");
        BillingDetails billingDetails = new BillingDetails();

        User user = new User(0, "test", "sadsa", "test@email", "test", "test@test.com", role, address, billingDetails, "Person Profile", true,
                "secret");

        AuthenticationRequest authenticationRequest = new AuthenticationRequest(user.getUsername(), user.getPassword(), "code");
        CustomUserDetails userDetails = new CustomUserDetails(user);
        when(userDetailsService.loadUserByUsername(user.getUsername())).thenReturn(userDetails);
        when(mfaService.verifyCode(user.getSecret(), authenticationRequest.getMfaCode())).thenReturn(true);
        int actualStatus =  authenticationController.createAuthenticationTokenAndLogin(authenticationRequest).getStatusCode().value();
        int expectedStatus = 200;

        Assertions.assertEquals(expectedStatus, actualStatus);
    }

    @Test
    void negativeTest_invalidCredentials() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("username", "password", "code");


        Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                authenticationRequest.getPassword());

        when(authenticationManager.authenticate(authentication)).thenThrow(BadCredentialsException.class);


        Assertions.assertThrows(Exception.class,
                () -> authenticationController.createAuthenticationTokenAndLogin(authenticationRequest)
        );
    }


    @Test
    void negativeTest_invalidCode() throws Exception {
        UserRole role = new UserRole(1, "admin");
        Address address = new Address(1, "1 something street", "TestYoyo city", "TestCity", "TestState", "TestCountry",
                "8823", "Test", "TestPlanet");
        BillingDetails billingDetails = new BillingDetails();

        User user = new User(0, "test", "sadsa", "test@email", "test", "test@test.com", role, address, billingDetails, "Person Profile", true,
                "secret");

        AuthenticationRequest authenticationRequest = new AuthenticationRequest(user.getUsername(), user.getPassword(), "code");
        CustomUserDetails userDetails = new CustomUserDetails(user);
        when(userDetailsService.loadUserByUsername(user.getUsername())).thenReturn(userDetails);
        when(mfaService.verifyCode(user.getSecret(), authenticationRequest.getMfaCode())).thenReturn(false);
        int actualStatus =  authenticationController.createAuthenticationTokenAndLogin(authenticationRequest).getStatusCode().value();
        int expectedStatus = 400;

        Assertions.assertEquals(expectedStatus, actualStatus);
    }

}
