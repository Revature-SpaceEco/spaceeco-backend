package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.OrderRepository;
import com.revature.spaceecobackend.dao.UserRepository;
import com.revature.spaceecobackend.dto.OrderDto;
import com.revature.spaceecobackend.exception.OrderNotFound;
import com.revature.spaceecobackend.model.Order;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    //TODO getAllOrders
    public List<OrderDto> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders){
            orderDtos.add(modelMapper.map(order,OrderDto.class));
        }
        return orderDtos;
    }

    //TODO getAllOrdersByUserId
    public List<OrderDto> getOrdersByBuyerId(int id) {
        if (userRepository.findById(id).isPresent()) {
            List<Order> orders = orderRepository.findByBuyerId(id);
            List<OrderDto> orderDtos = new ArrayList<>();
            for (Order order: orders) {
                orderDtos.add(modelMapper.map(order, OrderDto.class));
            }
            return orderDtos;
        } else {
            // throw new UserNotFound("A user with an id of " + id + " does not exist.");
            return null;
        }
    }

    //TODO getOrderByOrderId
    public OrderDto getOrderByOrderId(int id) throws OrderNotFound {
        Optional<Order> optional = orderRepository.findById(id);
        Order order;
        if(optional.isPresent()) {
            order = optional.get();
        } else {
            throw new OrderNotFound("An order with an id of " + id + " does not exist.");
        }
        return modelMapper.map(order, OrderDto.class);
    }

    //TODO createOrder
    public OrderDto createOrder(OrderDto dto){
        Order order = modelMapper.map(dto, Order.class);
        Order createdOrder = orderRepository.saveAndFlush(order);
        return modelMapper.map(createdOrder, OrderDto.class);
    }

    //TODO updateOrder
    public OrderDto updateOrder(OrderDto dto) {
        Order order = modelMapper.map(dto, Order.class);
        Order updatedOrder = orderRepository.saveAndFlush(order);
        return modelMapper.map(updatedOrder, OrderDto.class);
    }

    //TODO deleteOrder
    public boolean deleteOrder(OrderDto orderDto) throws OrderNotFound {
        Optional<Order> order = orderRepository.findById(orderDto.getId());
        if(order.isPresent()){
            orderRepository.delete(order.get());
        }else
        {
            throw new OrderNotFound("Order with id "+orderDto.getId()+" does not exist");
        }
        return true;
    }
}
