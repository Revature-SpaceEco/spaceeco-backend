package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.OrderRepository;
import com.revature.spaceecobackend.dto.OrderDto;
import com.revature.spaceecobackend.model.Order;
import com.revature.spaceecobackend.model.User;
import com.revature.spaceecobackend.model.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepo;

    @InjectMocks
    private OrderService orderService;

    private static User buyer;
    private static UserRole userRole;
    private static List<Order> orders;
    private static Order order;
    private static OrderDto orderDto;


    @BeforeAll
    public static void init() {
        orders = new ArrayList<>();
        order = new Order();
        orders.add(order);
        orderDto = new OrderDto();
    }

    //getting all orders
    @Test
    void getAllOrders_positive() {
        when(orderRepo.findAll()).thenReturn(orders);
        List<Order> actual = orderService.getAllOrders();

        assertThat(actual).isEqualTo(orders);
    }

    @Test
    void getOrderByBuyerId_ValidId() {
        when(orderRepo.findByBuyerId(1)).thenReturn(orders);

        List<Order> actual = orderService.getOrdersByBuyerId(1);
        assertThat(actual).isEqualTo(orders);
    }

//    @Test
//    void getOrderByBuyerId_InvalidId() {
//        when(orderRepo.findByBuyerId(500)).thenReturn(null);
//
//        Assertions.assertThrows(SomeException.class, () -> {
//                orderService.getOrdersByBuyerId(500);
//                });
//    }
    //getting order by id
    @Test
    void getOrderByValidOrderId(){
        when(orderRepo.findById(1)).thenReturn(Optional.of(order));
        Order actual = orderService.getOrderByOrderId(1);
        assertThat(actual).isEqualTo(order);
    }

    //create order
    @Test
    void createOrder_positive() {
        when(orderRepo.saveAndFlush(order)).thenReturn(order);
        Order actual = orderService.createOrder(orderDto);
        assertThat(actual).isEqualTo(order);
    }

    //udpate order
    @Test
    void updateOrder_positive(){
        OrderDto editedOrder = new OrderDto();
        when(orderRepo.saveAndFlush(order)).thenReturn(order);

        Order actual = orderService.updateOrder(editedOrder);
        assertThat(actual).isEqualTo(order);
    }
    //delete order
    @Test
    void deleteOrderWhenThatOrderExists() {
        when(orderRepo.existsById(order.getId())).thenReturn(true);
        assertThat(orderService.deleteOrder(order)).isTrue();
    }

}
