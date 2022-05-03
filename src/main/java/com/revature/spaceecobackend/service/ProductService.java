package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.ProductRepository;
import com.revature.spaceecobackend.dto.ProductDto;
import com.revature.spaceecobackend.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService implements ProductServiceInterface {

    @Autowired
    ProductRepository productRepo;

    @Autowired
    private ModelMapper modelMapper;



    public List<ProductDto> findAllProducts() {

        List<ProductDto> productDtos = new ArrayList<>();

        List<Product> products = productRepo.findAll();
        for (Product p : products) {
            productDtos.add(modelMapper.map(p, ProductDto.class));
        }
        return productDtos;
    }

    public ProductDto getProductsById(int productId) {
        Optional<Product> optional = productRepo.findById(productId);

        if (optional.isPresent()) {
            return modelMapper.map(optional.get(), ProductDto.class);
        }
        return null;
    }
}