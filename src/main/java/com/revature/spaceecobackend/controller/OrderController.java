package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.dto.OrderDto;
import com.revature.spaceecobackend.exception.OrderNotFound;
import com.revature.spaceecobackend.model.Order;
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
    public ResponseEntity<?> getAllOrders(){
        List<OrderDto> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            return ResponseEntity.badRequest().body("No orders Found");
        }
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<?> getOrderById(@PathVariable("order_id") int orderId){
        try{
            OrderDto orderDto = orderService.getOrderByOrderId(orderId);
        }catch(OrderNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return null;
    }

    @GetMapping("/user/{userId}/orders")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable("userId") int userId){
        return null;
    }

    @PatchMapping
    public ResponseEntity<?> updateOrderById( @RequestBody OrderDto orderDto) {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> addNewOrder(@RequestBody OrderDto orderDto) {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteOrder(@RequestBody OrderDto orderDto){
        try {
            boolean deleted = orderService.deleteOrder(orderDto);
        } catch(OrderNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return null;
    }
}
