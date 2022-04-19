package com.revature.spaceecobackend.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

/*
    // seller id
    @JoinColumn(name="seller_id",nullable = false)
    int sellerId;

    // buyer id
    @JoinColumn(name="buyer_id", nullable = false)
    @OneToOne
    private int buyerId;

    list of products
    @ManyToMany
    List<Product>items
*/
    // order date
    @Column(name="order_date",nullable = false)
    private Timestamp orderDate;

    // order status
    @Column(name="order_status", nullable = false)
    private String orderStatus;

    // shipping address
    @ManyToOne
    @JoinColumn(name ="shipping_address_id", nullable = false)
    private int shippingAddressId;

    // payment id
    @JoinColumn(name="payment_id")
    @OneToOne
    private Payment payment;
}
