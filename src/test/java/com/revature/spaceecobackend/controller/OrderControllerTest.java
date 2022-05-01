package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.dto.OrderDto;
import com.revature.spaceecobackend.dto.PaymentDto;
import com.revature.spaceecobackend.exception.EmptyFields;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.model.*;
import com.revature.spaceecobackend.service.OrderService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
  @Mock
  private OrderService orderService;

  @InjectMocks
  private OrderController orderController;

  private static User buyer;
  private static User seller;
  private static UserRole userRole;
  private static UserRole userRole2;
  private static List<Order> orders;
  private static Order order;
  private static OrderDto orderDto;
  private static List<OrderDto> orderDtos;
  private static List<Product> products;
  private static Payment payment;
  private static Categories category;
  private static Address address;
  private static PaymentDto paymentDto;
  private static BillingDetails billingDetails;
  private static Timestamp orderDate;

  @BeforeAll
  public static void init() {
    billingDetails = new BillingDetails();
    userRole2 = new UserRole(2, "seller");
    seller = new User(2, "seller", "password", "email@email.com", "tester", "testy", userRole2, address, billingDetails, "www.image.com", true);
    category = new Categories(1, "categoryTest");
    products = new ArrayList<>();
    products.add(new Product(1, "test", "test description", 100, category, "image.jpg", seller));
    products.add(new Product(2, "test", "test description", 100, category, "image2.jpg", seller));
    payment = new Payment();
    userRole = new UserRole(1, "buyer");
    buyer = new User(1, "test", "password", "email@email.com", "tester", "testy", userRole, address, billingDetails, "www.image.com", true);
    address = new Address(1, "10 Test Drive", null, "City Test", "Test", "Country", "80000", "Solar", "Planet");
    orders = new ArrayList<>();
    orderDate = new Timestamp(System.currentTimeMillis());
    order = new Order(1, buyer, products, orderDate, "pending", address, payment);
    orders.add(order);
    paymentDto = new PaymentDto();
    orderDtos = new ArrayList<>();
    orderDto = new OrderDto(1, buyerDto,productsDto,orderDate, "pending", address, paymentDto);
    orderDtos.add(orderDto);
  }


  @Test
  void getAllOrders_positive() {
    when(orderService.getAllOrders()).thenReturn(orderDtos);
    ResponseEntity<?> response = orderController.getAllOrders();
    assertThat(response.getBody()).isEqualTo(orderDtos);
  }

  @Test
  void getOrderById_positive() throws NotFound {
    when(orderService.getOrderByOrderId(orderDto.getId())).thenReturn(orderDto);
    ResponseEntity<?> response = orderController.getOrderById(orderDto.getId());
    assertThat(response.getBody()).isEqualTo(orderDto);
  }

  @Test
  void getOrderById_OrderNotFound() throws NotFound {
    when(orderService.getOrderByOrderId(any(Integer.class))).thenThrow(NotFound.class);
    ResponseEntity<?> response = orderController.getOrderById(500);
    assertThat(response.getStatusCodeValue()).isEqualTo(404);
  }

  @Test
  void updateOrderById_positive() throws NotFound, EmptyFields {
    when(orderService.updateOrder(orderDto)).thenReturn(orderDto);
    ResponseEntity<?> response = orderController.updateOrderById(orderDto);
    assertThat(response.getBody()).isEqualTo(orderDto);
  }

  @Test
  void updateOrderById_OrderNotFound() throws NotFound, EmptyFields {
    when(orderService.updateOrder(orderDto)).thenThrow(NotFound.class);
    ResponseEntity<?> response = orderController.updateOrderById(orderDto);
    int expected = 404;
    assertThat(response.getStatusCodeValue()).isEqualTo(expected);
  }

  @Test
  void updateOrderById_EmptyFields() throws NotFound, EmptyFields {
    when(orderService.updateOrder(any(OrderDto.class))).thenThrow(EmptyFields.class);
    ResponseEntity<?> response = orderController.updateOrderById(new OrderDto());

    assertThat(response.getStatusCodeValue()).isEqualTo(400);
  }


  @Test
  void deleteOrder_positive() throws NotFound {
    when(orderService.deleteOrder(orderDto)).thenReturn(true);
    ResponseEntity<?> response = orderController.deleteOrder(orderDto);
    assertThat(response.getBody()).isEqualTo(true);
  }

  @Test
  void deleteOrder_OrderNotFound() throws NotFound {
    when(orderService.deleteOrder(orderDto)).thenThrow(NotFound.class);
    ResponseEntity<?> response = orderController.deleteOrder(orderDto);
    int expected = 404;
    assertThat(response.getStatusCodeValue()).isEqualTo(expected);
  }

  @Test
  void createOrder_positive() throws EmptyFields {
    when(orderService.createOrder(orderDto)).thenReturn(orderDto);
    ResponseEntity<?> response = orderController.addNewOrder(orderDto);
    assertThat(response.getBody()).isEqualTo(orderDto);
  }

  @Test
  void createOrder_EmptyFields() throws EmptyFields {
    when(orderService.createOrder(orderDto)).thenThrow(EmptyFields.class);
    ResponseEntity<?> response = orderController.addNewOrder(orderDto);
    int expected = 400;
    assertThat(response.getStatusCodeValue()).isEqualTo(expected);
  }
}
