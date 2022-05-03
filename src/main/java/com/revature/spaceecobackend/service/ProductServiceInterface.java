package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dto.ProductDto;

import java.util.List;

public interface ProductServiceInterface {


    List<ProductDto> findAllProducts();
}