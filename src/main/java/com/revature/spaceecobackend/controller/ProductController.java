package com.revature.spaceecobackend.controller;


import com.revature.spaceecobackend.dto.ProductDto;
import com.revature.spaceecobackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {

        return productService.findAllProducts();


    }

    @GetMapping("/products/{product_id}")
    public ProductDto getProductById(@PathVariable("product_id") int productId) {
        return productService.getProductsById(productId);
    }

}

