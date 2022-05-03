package com.revature.spaceecobackend.service;


import com.revature.spaceecobackend.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserSecurityTest {

    @Mock
    private Authentication authentication;


    private UserSecurity userSecurity = new UserSecurity();

    @Test
    void hasUserId_positiveTest(){
        UserRole role = new UserRole(1, "admin");
        Address address = new Address(1, "1 something street", "TestYoyo city", "TestCity", "TestState", "TestCountry",
                "8823", "Test", "TestPlanet");
        BillingDetails billingDetails = new BillingDetails();
        User user = new User(0, "test", "sadsa", "test@email", "test", "test@test.com", role, address, billingDetails, "Person Profile", true,
                "secret");

        CustomUserDetails userDetails = new CustomUserDetails(user);

        when(authentication.getPrincipal()).thenReturn(userDetails);

        Assertions.assertTrue(userSecurity.hasUserId(authentication,"0"));
    }
}
