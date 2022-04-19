package com.revature.spaceecobackend;


import com.revature.spaceecobackend.dao.ProductRepository;
import com.revature.spaceecobackend.model.Product;
import com.revature.spaceecobackend.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    public void get_all_products_positive(){
        List<Product> fakeProducts = new ArrayList<>();
        fakeProducts.add(new Product(1,"product","product description",100,1,"image.jpg", 1));


        when(productRepository.findAll()).thenReturn(fakeProducts);

        List<Product> actual = productService.findAll();

        Assertions.assertEquals(fakeProducts,actual);

    }
}