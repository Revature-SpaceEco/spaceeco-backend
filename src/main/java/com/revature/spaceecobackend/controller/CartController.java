package com.revature.spaceecobackend.controller;

import com.revature.spaceecobackend.dto.CartDto;
import com.revature.spaceecobackend.dto.OrderDto;
import com.revature.spaceecobackend.dto.UserDTO;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users/{user_id}/cart")
@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
public class CartController {
  @Autowired
  private CartService cartService;

  @GetMapping
  public ResponseEntity<?> getCartByUserId(@PathVariable("user_id") int userId){
    try {
      CartDto cartDto = cartService.getCartByBuyerId(userId);

      return ResponseEntity.ok().body(cartDto);
    } catch (NotFound e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<?> createCart(@PathVariable("user_id") int userId){
    return null;
  }

  @PatchMapping
  public ResponseEntity<?> updateCart(@PathVariable("user_id") int userId){
    return null;
  }
}
