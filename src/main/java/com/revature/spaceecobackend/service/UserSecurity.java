package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.model.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userSecurity")
public class UserSecurity {

    public boolean hasUserId(Authentication authentication, String userId) {
        Integer id = Integer.parseInt(userId);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getId().equals(id);
    }
}
