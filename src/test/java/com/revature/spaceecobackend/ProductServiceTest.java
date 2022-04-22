package com.revature.spaceecobackend;


import com.revature.spaceecobackend.dao.ProductRepository;
import com.revature.spaceecobackend.model.Categories;
import com.revature.spaceecobackend.model.Product;
import com.revature.spaceecobackend.model.User;
import com.revature.spaceecobackend.model.UserRole;
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
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    void get_all_products_positive(){
        UserRole fakeRole = new UserRole();
        fakeRole.setId(1);
        fakeRole.setRole("role");
        Categories categories=new Categories(1,"Electronics");
        User fakeUser = new User();
        fakeUser.setId(1);
        fakeUser.setEmail("email.com");
        fakeUser.setFirstName("firstname");
        fakeUser.setLastName("lastname");
        fakeUser.setImageUrl("image.jpg");
        fakeUser.setUsername("username");
        fakeUser.setPassword("password");
        fakeUser.setUserRoleId(fakeRole);
        List<Product> fakeProducts = new ArrayList<>();
        fakeProducts.add(new Product(1,"product","product description",100,categories,"image.jpg", fakeUser));


        when(productRepository.findAll()).thenReturn(fakeProducts);

        List<Product> actual = productService.findAll();

        Assertions.assertEquals(fakeProducts,actual);

    }
}