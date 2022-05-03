package com.revature.spaceecobackend.dto;

import com.revature.spaceecobackend.model.Address;
import com.revature.spaceecobackend.model.BillingDetails;
import com.revature.spaceecobackend.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
  private int id;
  private String username;
  private String email;
  private String firstName;
  private String lastName;
  private UserRole userRole;
  private Address primaryAddress;
  private BillingDetails primaryBilling;
  private String imageUrl;
  private String qrCode;
}