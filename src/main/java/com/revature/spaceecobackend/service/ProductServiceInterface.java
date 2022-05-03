package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dto.ProductDto;
import com.revature.spaceecobackend.model.Product;

import java.util.List;

public interface ProductServiceInterface {


  List<ProductDto> findAllProducts();
}