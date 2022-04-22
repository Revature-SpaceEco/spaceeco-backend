package com.revature.spaceecobackend.dto;

import com.revature.spaceecobackend.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.sql.Timestamp;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private int id;

//    private UserDto buyer;

//    List<ProductDTO> items;

    private Timestamp orderDate;

    private String orderStatus;

    private Address shippingAddressId;

    private PaymentDto payment;
}
