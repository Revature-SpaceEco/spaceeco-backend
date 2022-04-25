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
@RequestMapping("/orders")
@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
public class OrderController {
  @Autowired
  private OrderService orderService;

  @GetMapping
  public ResponseEntity<?> getAllOrders() {
    List<OrderDto> orders = orderService.getAllOrders();
    if (orders.isEmpty()) {
      return ResponseEntity.badRequest().body("No orders Found");
    }
    return ResponseEntity.ok().body(orders);
  }

  @GetMapping("/{order_id}")
  public ResponseEntity<?> getOrderById(@PathVariable("order_id") int orderId) {
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

  @PatchMapping
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

  @PostMapping
  public ResponseEntity<?> addNewOrder(@RequestBody OrderDto orderDto) {
    try {
      OrderDto newOrder = orderService.createOrder(orderDto);
      return ResponseEntity.ok().body(newOrder);
    } catch (EmptyFields e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping
  public ResponseEntity<?> deleteOrder(@RequestBody OrderDto orderDto) {
    try {
      boolean deleted = orderService.deleteOrder(orderDto);
      return ResponseEntity.ok().body(deleted);
    } catch (NotFound e) {
      return ResponseEntity.notFound().build();
    }
  }
}
