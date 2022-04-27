package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.ProductRepository;
import com.revature.spaceecobackend.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class ProductService implements ProductServiceInterface {

  @Autowired
  ProductRepository productRepo;

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public  Product getProductsById(Integer productId) {
        Optional<Product> optional = productRepo.findById(productId);
        return  optional.get();}
}