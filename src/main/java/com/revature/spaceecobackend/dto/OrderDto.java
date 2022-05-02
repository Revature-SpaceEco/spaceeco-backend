package com.revature.spaceecobackend.dto;

import com.revature.spaceecobackend.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
  private int id;

  private UserDTO buyer;

  List<ProductDto> items;

  private Timestamp orderDate;

  private String orderStatus;

  private Address shippingAddressId;

  private PaymentDto payment;
}