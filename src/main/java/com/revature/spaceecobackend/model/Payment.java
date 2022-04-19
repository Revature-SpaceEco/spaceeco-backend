package com.revature.spaceecobackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="payments")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "billing_address_id")
    @OneToOne
    private int billingId;

    @Column(name="payment_type", nullable=false)
    private String type;

    @Column(name="payment_status", nullable=false)
    private String status;
}
