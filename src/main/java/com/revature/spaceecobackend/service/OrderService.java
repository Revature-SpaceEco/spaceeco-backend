package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.OrderRepository;
import com.revature.spaceecobackend.dao.UserRepository;
import com.revature.spaceecobackend.dto.OrderDto;
import com.revature.spaceecobackend.exception.EmptyFields;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<OrderDto> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders){
            orderDtos.add(modelMapper.map(order,OrderDto.class));
        }
        return orderDtos;
    }


    public List<OrderDto> getOrdersByBuyerId(int id) throws NotFound {
        if (userRepository.findById(id).isPresent()) {
            List<Order> orders = orderRepository.findByBuyerId(id);
            List<OrderDto> orderDtos = new ArrayList<>();
            for (Order order: orders) {
                orderDtos.add(modelMapper.map(order, OrderDto.class));
            }
            return orderDtos;
        } else {
            throw new NotFound("A user with an id of " + id + " does not exist.");
        }
    }


    public OrderDto getOrderByOrderId(int id) throws NotFound {
        Optional<Order> optional = orderRepository.findById(id);
        Order order;
        if(optional.isPresent()) {
            order = optional.get();
        } else {
            throw new NotFound("An order with an id of " + id + " does not exist.");
        }
        return modelMapper.map(order, OrderDto.class);
    }

    public OrderDto createOrder(OrderDto dto) throws EmptyFields {
        if(Stream.of(dto).anyMatch(Objects::isNull)){
            throw new EmptyFields("Order is missing information");
        }

        Order order = modelMapper.map(dto, Order.class);
        Order createdOrder = orderRepository.saveAndFlush(order);
        return modelMapper.map(createdOrder, OrderDto.class);
    }

    public OrderDto updateOrder(OrderDto dto) throws NotFound, EmptyFields {
        if(Stream.of(dto).anyMatch(Objects::isNull)){
            throw new EmptyFields("Order is missing information");
        }
        
        Optional<Order> optional = orderRepository.findById(dto.getId());
        if (!optional.isPresent()) throw new NotFound("Order with id "+dto.getId()+" does not exist");
        Order order = modelMapper.map(dto, Order.class);
        Order updatedOrder = orderRepository.saveAndFlush(order);
        return modelMapper.map(updatedOrder, OrderDto.class);
    }

    public boolean deleteOrder(OrderDto orderDto) throws NotFound {
        Optional<Order> order = orderRepository.findById(orderDto.getId());
        if(order.isPresent()){
            orderRepository.delete(order.get());
        }else
        {
            throw new NotFound("Order with id "+orderDto.getId()+" does not exist");
        }
        return true;
    }
}
