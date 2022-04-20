package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.OrderRepository;
import com.revature.spaceecobackend.dto.OrderDto;
import com.revature.spaceecobackend.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    //TODO getAllOrders
    public List<Order> getAllOrders(){
        return null;
    }

    //TODO getAllOrdersByUserId
    public List<Order> getOrdersByBuyerId(int id) {
        return null;
    }

    //TODO getOrderByOrderId
    public Order getOrderByOrderId(int id){
        return null;
    }

    //TODO createOrder
    public Order createOrder(OrderDto order){
        return null;
    }

    //TODO updateOrder
    public Order updateOrder(OrderDto order) {
        return null;
    }

    //TODO deleteOrder
    public boolean deleteOrder(Order order){
        return false;
    }
}
