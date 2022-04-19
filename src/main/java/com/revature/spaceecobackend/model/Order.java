package com.revature.spaceecobackend.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

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
        private User sellerId;

        // buyer id
        @JoinColumn(name="buyer_id", nullable = false)
        @OneToOne
        private User buyerId;

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
//    @ManyToOne
//    @JoinColumn(name ="shipping_address_id", nullable = false)
//    private Address shippingAddressId;

    // payment id
    @JoinColumn(name="payment_id")
    @OneToOne
    private Payment payment;


}