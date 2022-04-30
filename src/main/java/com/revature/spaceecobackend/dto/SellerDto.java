package com.revature.spaceecobackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerDto {

    private int id;
    private String username;
    private String email;
    private String firstName;
    private boolean active;
}
