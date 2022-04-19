package com.revature.spaceecobackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "billing_details")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BillingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="billing_card_type", nullable = false)
    private String billingCardType;

    @Column(name="billing_card_number", nullable = false)
    private long billingCardNumber;

    @Column(name = "billing_security_number", nullable = false)
    private int billingSecurityNumber;

    @Column(name = "billing_name", nullable = false)
    private String billingName;

//    @JoinColumn(name="billing_address")
//    @ManyToOne
//    private Address billingAddress;


}