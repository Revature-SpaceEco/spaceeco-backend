package com.revature.spaceecobackend.dto;

import com.revature.spaceecobackend.model.Address;
import lombok.Data;


import java.sql.Timestamp;
import java.util.List;
import com.revature.spaceecobackend.dto.PaymentDto;
@Data
public class OrderDto {
    private int id;

//    private UserDto buyerId;

//    List<ProductDTO> items;

    private Timestamp orderDate;

    private String orderStatus;

    private Address shippingAddressId;

    private PaymentDto payment;
}
