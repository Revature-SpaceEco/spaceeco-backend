package com.revature.spaceecobackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "products")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column
    private String productName;

    @Column
    private String productDescription;

    @Column
    private Integer productCost ;

    @Column
    private Integer categoryId ;

    @Column
    private String productImage;

    @Column
    private Integer productSellerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId && Objects.equals(productName, product.productName) && Objects.equals(productDescription, product.productDescription) && Objects.equals(productCost, product.productCost) && Objects.equals(categoryId, product.categoryId) && Objects.equals(productImage, product.productImage) && Objects.equals(productSellerId, product.productSellerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productDescription, productCost, categoryId, productImage, productSellerId);
    }
}