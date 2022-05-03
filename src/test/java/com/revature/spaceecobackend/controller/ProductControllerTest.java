package com.revature.spaceecobackend.controller;
import com.revature.spaceecobackend.dto.ProductDto;
import com.revature.spaceecobackend.dto.SellerDto;
import com.revature.spaceecobackend.model.Categories;
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
public class ProductControllerTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController prodController;

    @Test
    public void testGetAllProducts_positive() {
        Categories categories = new Categories(1,"categoryName");
        SellerDto sdto = new SellerDto(1,"username","email","firstname",true);
        List<ProductDto> productDtos = new ArrayList<>();
        ProductDto pdto = new ProductDto(1,"name","description",1,categories.getName(),"image",sdto);
        productDtos.add(pdto);
        when(productService.findAllProducts()).thenReturn(productDtos);
        List<ProductDto> response = prodController.getAllProducts();
        Assertions.assertEquals(productDtos,response);
    }

    @Test
    public void getProductByIdPositive(){
        Categories categories = new Categories(1,"categoryName");
        SellerDto sdto = new SellerDto(1,"username","email","firstname",true);
        ProductDto pdto = new ProductDto(1,"name","description",1,categories.getName(),"image",sdto);
        when(productService.getProductsById(1)).thenReturn(pdto);
        ProductDto response = prodController.getProductById(1);
        Assertions.assertEquals(pdto,response);
    }







}
