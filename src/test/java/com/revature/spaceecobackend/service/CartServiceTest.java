package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.CartRepository;
import com.revature.spaceecobackend.dao.UserRepository;
import com.revature.spaceecobackend.dto.CartDto;
import com.revature.spaceecobackend.dto.UserDTO;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import static org.mockito.ArgumentMatchers.any;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {
  @Mock
  CartRepository cartRepository;

  @Mock
  UserRepository userRepository;

  @Spy
  ModelMapper modelMapper;

  @InjectMocks
  CartService cartService;

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
  public void getCartByUserId_positive() throws NotFound {
    when(userRepository.findById(buyer.getId())).thenReturn(Optional.of(buyer));
    when(cartRepository.findByBuyerId(buyer.getId())).thenReturn(cart);

    CartDto actual = cartService.getCartByBuyerId(1);
    assertThat(actual).isEqualTo(cartDto);
  }

  @Test
  void getOrderByBuyerId_UserNotFound() {
    Assertions.assertThrows(NotFound.class, () -> {
      cartService.getCartByBuyerId(500);
    });
  }

  @Test
  public void createCart_positive() throws NotFound {
    when(cartRepository.saveAndFlush(any(Cart.class))).thenReturn(cart);

    CartDto actual = cartService.createCart(cartDto);
    assertThat(actual).isEqualTo(cartDto);
  }

  @Test
  void createCart_UserNotFound() {
    Assertions.assertThrows(NotFound.class, () -> {
      cartService.createCart(new CartDto());
    });
  }



  @Test
  public void updateCart_positive() throws NotFound {
    CartDto editedCart = new CartDto(1,buyerDto,products);
    when(cartRepository.findById(editedCart.getId())).thenReturn(Optional.of(cart));
    when(cartRepository.saveAndFlush(any(Cart.class))).thenReturn(cart);

    CartDto actual = cartService.updateCart(editedCart);
    assertThat(actual).isEqualTo(cartDto);
  }

  @Test
  void updateCart_UserNotFound() {
    Assertions.assertThrows(NotFound.class, () -> {
      cartService.updateCart(new CartDto());
    });
  }

}
