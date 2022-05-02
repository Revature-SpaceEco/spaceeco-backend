package com.revature.spaceecobackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @JoinColumn(name = "buyer", nullable = false)
  @OneToOne
  private User buyer;

  @ManyToMany
  List<Product> items;

  @Column(name = "order_date", nullable = false)
  private Timestamp orderDate;

  @Column(name = "order_status", nullable = false)
  private String orderStatus;

  @ManyToOne
  @JoinColumn(name = "shipping_address_id", nullable = false)
  private Address shippingAddressId;

  @JoinColumn(name = "payment_id")
  @OneToOne
  private Payment payment;
}