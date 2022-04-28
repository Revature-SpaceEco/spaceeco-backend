package com.revature.spaceecobackend.dto;

import com.revature.spaceecobackend.dto.UserDTO;
import com.revature.spaceecobackend.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
  private int id;
  private UserDTO buyer;
  List<Product> products;
}
