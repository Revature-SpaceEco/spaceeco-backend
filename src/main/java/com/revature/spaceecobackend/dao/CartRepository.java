package com.revature.spaceecobackend.dao;

import com.revature.spaceecobackend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
  Cart findByBuyerId(int id);
}
