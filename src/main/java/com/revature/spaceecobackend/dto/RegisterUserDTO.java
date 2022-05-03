package com.revature.spaceecobackend.dto;

import com.revature.spaceecobackend.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDTO {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private UserRole userRole;
}
