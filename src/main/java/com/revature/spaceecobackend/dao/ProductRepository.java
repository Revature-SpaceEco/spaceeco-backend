package com.revature.spaceecobackend.dao;

import com.revature.spaceecobackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}