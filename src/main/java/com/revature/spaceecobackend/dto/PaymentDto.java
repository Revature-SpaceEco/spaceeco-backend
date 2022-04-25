package com.revature.spaceecobackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private int id;
    private String status;
    private BillingDetailsDto billingDetailsDto;
}
