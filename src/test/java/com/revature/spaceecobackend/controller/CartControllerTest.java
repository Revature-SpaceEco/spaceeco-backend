package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.dto.CartDto;
import com.revature.spaceecobackend.dto.UserDTO;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.model.*;
import com.revature.spaceecobackend.service.CartService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartControllerTest {
  @Mock
  CartService cartService;

  @InjectMocks
  CartController cartController;

  private static Address address;
  private static BillingDetails billingDetails;
  private static Cart cart;
  private static CartDto cartDto;
  private static UserRole buyerRole;
  private static UserRole sellerRole;
  private static User buyer;
  private static User seller;
  private static UserDTO buyerDto;
  private static Categories categories;
  private static List<Product> products;

  @BeforeAll
  public static void init(){
    buyerRole = new UserRole(1,"ROLE_BUYER");
    sellerRole = new UserRole(2,"ROLE_SELLER");
    address = new Address(1, "123 Fake St", null, "Springfield", "Kentucky", "United States of America", "80000", "Solar System", "Earth");
    billingDetails = new BillingDetails(1, "MasterCard", 4519777777777777L, 123, "Homer Simpson", address);
    buyer = new User(1,"MrPlow","thatsMyName","mrplow@mrplow.com","Homer","Simpson",buyerRole,address,billingDetails,"https://storage.googleapis.com/project-1-images/Homer.PNG",true);
    seller = new User(1,"PlowKing","letitring","plowking@plowking.com","Barney","Gumble",sellerRole,address,billingDetails,"https://storage.googleapis.com/project-1-images/barney.PNG",true);
    buyerDto = new UserDTO(1,"MrPlow","mrplow@mrplow.com","Homer","Simpson",buyerRole,address,billingDetails,"https://storage.googleapis.com/project-1-images/Homer.PNG",true);
    categories = new Categories(1, "Electronics");
    products = new ArrayList<>();
    products.add(new Product(1, "product", "product description", 100, categories, "image.jpg", seller));
    products.add(new Product(2, "product", "product description", 200, categories, "image.jpg", seller));
    products.add(new Product(3, "product", "product description", 300, categories, "image.jpg", seller));
    cart = new Cart(1,buyer,products);
    cartDto = new CartDto(1,buyerDto,products);
  }

  @Test
  void getCartByUserId() throws NotFound {
    when(cartService.getCartByBuyerId(buyer.getId())).thenReturn(cartDto);

    ResponseEntity response = cartController.getCartByUserId(buyer.getId());

    assertThat(response.getBody()).isEqualTo(cartDto);
  }

  @Test
  void getCartByUserId_UserNotFound() throws NotFound {
    when(cartService.getCartByBuyerId(any(Integer.class))).thenThrow(NotFound.class);
    ResponseEntity<?> response = cartController.getCartByUserId(500);
    assertThat(response.getStatusCodeValue()).isEqualTo(404);
  }

  @Test
  void createCart() throws NotFound {
    when(cartService.createCart(cartDto)).thenReturn(cartDto);

    ResponseEntity response = cartController.createCart(cartDto);

    assertThat(response.getBody()).isEqualTo(cartDto);
  }

  @Test
  void createCart_UserNotFound() throws NotFound {
    when(cartService.createCart(new CartDto())).thenThrow(NotFound.class);
    ResponseEntity<?> response = cartController.createCart(new CartDto());
    assertThat(response.getStatusCodeValue()).isEqualTo(400);
  }

  @Test
  void updateCart() throws NotFound {
    CartDto editedCart = new CartDto(1,buyerDto,products);
    when(cartService.updateCart(editedCart)).thenReturn(editedCart);

    ResponseEntity response = cartController.updateCart(editedCart);

    assertThat(response.getBody()).isEqualTo(editedCart);

  }

  @Test
  void updateCart_UserNotFound() throws NotFound {
    CartDto editedCart = new CartDto(1,buyerDto,products);
    when(cartService.updateCart(editedCart)).thenThrow(NotFound.class);
    ResponseEntity<?> response = cartController.updateCart(editedCart);
    assertThat(response.getStatusCodeValue()).isEqualTo(404);
  }
}
