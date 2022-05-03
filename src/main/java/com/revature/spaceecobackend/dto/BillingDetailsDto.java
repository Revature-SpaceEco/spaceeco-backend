package com.revature.spaceecobackend.dto;

import com.revature.spaceecobackend.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingDetailsDto {

  private String cardType;
  private long cardNumber;
  private int securityNumber;
  private String name;
  private Address address;
}