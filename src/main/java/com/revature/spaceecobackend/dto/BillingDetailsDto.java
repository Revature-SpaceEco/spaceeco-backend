package com.revature.spaceecobackend.dto;

import com.revature.spaceecobackend.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingDetailsDto {

  private String billingCardType;
  private long billingCardNumber;
  private int billingSecurityNumber;
  private String billingName;
  private Address billingAddress;
}