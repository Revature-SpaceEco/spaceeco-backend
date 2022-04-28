package com.revature.spaceecobackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_cart")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @JoinColumn(name="user_id")
  @OneToOne
  private User buyer;

  @JoinColumn(name="product_id")
  @OneToMany
  List<Product> products;
 }
