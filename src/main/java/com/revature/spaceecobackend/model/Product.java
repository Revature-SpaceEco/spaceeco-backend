package com.revature.spaceecobackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "product_name")
  private String name;

  @Column(name = "product_description")
  private String description;

  @Column(name = "product_cost")
  private Integer cost;

  @ManyToOne
  private Categories categories;

  @Column(name = "product_image")
  private String image;

  @ManyToOne
  private User seller;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return id == product.id && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(cost, product.cost) && Objects.equals(categories, product.categories) && Objects.equals(image, product.image) && Objects.equals(seller, product.seller);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, cost, categories, image, seller);
  }
}