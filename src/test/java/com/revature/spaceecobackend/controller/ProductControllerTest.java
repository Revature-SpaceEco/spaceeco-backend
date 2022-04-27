package com.revature.spaceecobackend.controller;


import com.revature.spaceecobackend.dao.ProductRepository;
import com.revature.spaceecobackend.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController prodController;


    /*
    @Test
    public void testGetAllProducts_positive() {

    }

     */




}
