package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.dto.OrderDto;
import com.revature.spaceecobackend.exception.EmptyFields;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
public class OrderController {
  @Autowired
  private OrderService orderService;

  @GetMapping("/admin/orders")
  public ResponseEntity<?> getAllOrders() {
    List<OrderDto> orders = orderService.getAllOrders();
    if (orders.isEmpty()) {
      return ResponseEntity.badRequest().body("No orders Found");
    }
    return ResponseEntity.ok().body(orders);
  }

  @GetMapping("/users/{userId}/orders/{orderId}")
  public ResponseEntity<?> getOrderById(@PathVariable("orderId") int orderId) {
    try {
      OrderDto orderDto = orderService.getOrderByOrderId(orderId);
      return ResponseEntity.ok().body(orderDto);
    } catch (NotFound e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/user/{userId}/orders")
  public ResponseEntity<?> getOrdersByUserId(@PathVariable("userId") int userId) {
    try {
      List<OrderDto> orders = orderService.getOrdersByBuyerId(userId);
      if (orders.isEmpty()) {
        return ResponseEntity.badRequest().body("No orders were found for this user.");
      }
      return ResponseEntity.ok().body(orders);
    } catch (NotFound e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PatchMapping("/users/{userId}/orders/{orderId}")
  public ResponseEntity<?> updateOrderById(@RequestBody OrderDto orderDto) {
    try {
      OrderDto updatedOrder = orderService.updateOrder(orderDto);
      return ResponseEntity.ok().body(updatedOrder);
    } catch (EmptyFields e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (NotFound e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/users/{userId}/orders")
  public ResponseEntity<?> addNewOrder(@RequestBody OrderDto orderDto) {
    try {
      OrderDto newOrder = orderService.createOrder(orderDto);
      return ResponseEntity.ok().body(newOrder);
    } catch (EmptyFields e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping("/users/{userId}/orders/{orderId}")
  public ResponseEntity<?> deleteOrder(@RequestBody OrderDto orderDto) {
    try {
      boolean deleted = orderService.deleteOrder(orderDto);
      return ResponseEntity.ok().body(deleted);
    } catch (NotFound e) {
      return ResponseEntity.notFound().build();
    }
  }
}