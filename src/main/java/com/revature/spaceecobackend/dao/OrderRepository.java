package com.revature.spaceecobackend.dao;

import com.revature.spaceecobackend.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    public abstract List<Order> findByBuyerId(int id);

}
