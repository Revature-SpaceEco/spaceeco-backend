package com.revature.spaceecobackend.dao;

import com.revature.spaceecobackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
  List<Order> findByBuyerId(int id);

}
