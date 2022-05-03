package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.ProductRepository;
import com.revature.spaceecobackend.dto.ProductDto;
import com.revature.spaceecobackend.dto.SellerDto;
import com.revature.spaceecobackend.model.Categories;
import com.revature.spaceecobackend.model.Product;
import com.revature.spaceecobackend.model.User;
import com.revature.spaceecobackend.model.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    ProductService productService;


    @Test
    void get_all_products_positive() {
        UserRole fakeRole = new UserRole();
        fakeRole.setId(1);
        fakeRole.setRole("role");
        Categories categories = new Categories(1, "Electronics");
        User fakeUser = new User();
        fakeUser.setId(1);
        fakeUser.setEmail("email.com");
        fakeUser.setFirstName("firstname");
        fakeUser.setLastName("lastname");
        fakeUser.setImageUrl("image.jpg");
        fakeUser.setUsername("username");
        fakeUser.setPassword("password");
        fakeUser.setActive(true);
        fakeUser.setUserRole(fakeRole);
        List<Product> fakeProducts = new ArrayList<>();
        fakeProducts.add(new Product(1, "product", "product description", 100, categories, "image.jpg", fakeUser));
        when(productRepository.findAll()).thenReturn(fakeProducts);
        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(new ProductDto(1, "product", "product description", 100, categories, "image.jpg", new SellerDto(fakeUser.getId(), fakeUser.getUsername(), fakeUser.getEmail(), fakeUser.getFirstName(), fakeUser.isActive())));
        List<ProductDto> actual = productService.findAllProducts();

        Assertions.assertEquals(productDtos, actual);
    }

    @Test
    void get_all_productsById_positive() {
        UserRole fakeRole = new UserRole();
        fakeRole.setId(1);
        fakeRole.setRole("role");
        Categories categories = new Categories(1, "Electronics");
        User fakeUser = new User();
        fakeUser.setId(1);
        fakeUser.setEmail("email.com");
        fakeUser.setFirstName("firstname");
        fakeUser.setLastName("lastname");
        fakeUser.setImageUrl("image.jpg");
        fakeUser.setUsername("username");
        fakeUser.setPassword("password");
        fakeUser.setActive(true);
        fakeUser.setUserRole(fakeRole);
        Product fakeProduct = new Product(1, "product", "product description", 100, categories, "image.jpg", fakeUser);

        when(productRepository.findById(1)).thenReturn(Optional.of(fakeProduct));

        ProductDto fakeDto = new ProductDto(1, "product", "product description", 100, categories, "image.jpg", new SellerDto(fakeUser.getId(), fakeUser.getUsername(), fakeUser.getEmail(), fakeUser.getFirstName(), fakeUser.isActive()));

        ProductDto actual = productService.getProductsById(1);

        Assertions.assertEquals(fakeDto, actual);

    }
  
}