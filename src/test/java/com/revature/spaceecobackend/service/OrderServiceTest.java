package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.OrderRepository;
import com.revature.spaceecobackend.dao.UserRepository;
import com.revature.spaceecobackend.dto.OrderDto;
import com.revature.spaceecobackend.dto.PaymentDto;
import com.revature.spaceecobackend.exception.OrderNotFound;
import com.revature.spaceecobackend.exception.UserNotFound;
import com.revature.spaceecobackend.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepo;

    @Mock
    private UserRepository userRepo;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private OrderService orderService;

    private static User buyer;
    private static User seller;
    private static UserRole userRole;
    private static UserRole userRole2;
    private static List<Order> orders;
    private static Order order;
    private static OrderDto orderDto;
    private static List<OrderDto> orderDtos;
    private static List<Product> products;
    private static Payment payment;
    private static Categories category;
    private static Address address;
    private static PaymentDto paymentDto;


    @BeforeAll
    public static void init() {
        userRole2 = new UserRole(2, "seller");
        seller = new User(2, "seller", "password", "email@email.com", "tester", "testy", userRole2, "www.image.com");
        category = new Categories(1, "categoryTest");
        products = new ArrayList<>();
        products.add(new Product(1,"test", "test description", 100, category, "image.jpg", seller));
        products.add(new Product(2,"test", "test description", 100, category, "image2.jpg", seller));
        payment = new Payment();
        userRole = new UserRole(1, "buyer");
        buyer = new User(1, "test", "password", "email@email.com", "tester", "testy", userRole, "www.image.com");
        address = new Address(1, "10 Test Drive", null, "City Test", "Test", "Country", "80000", "Solar", "Planet");
        orders = new ArrayList<>();
        Timestamp orderDate = new Timestamp(System.currentTimeMillis());
        order = new Order(1, buyer, products, orderDate, "pending", address, payment);
        orders.add(order);
        paymentDto = new PaymentDto(0, null);
        orderDtos = new ArrayList<>();
        orderDto = new OrderDto(1, orderDate, "pending", address, paymentDto);
        orderDtos.add(orderDto);
    }

    //getting all orders
    @Test
    void getAllOrders_positive() {
        when(orderRepo.findAll()).thenReturn(orders);
        List<OrderDto> actual = orderService.getAllOrders();

        assertThat(actual).isEqualTo(orderDtos);
    }

    @Test
    void getOrderByBuyerId_ValidId() throws UserNotFound {
        when(userRepo.findById(1)).thenReturn(Optional.of(buyer));
        when(orderRepo.findByBuyerId(1)).thenReturn(orders);

        List<OrderDto> actual = orderService.getOrdersByBuyerId(1);
        assertThat(actual).isEqualTo(orderDtos);
    }

    @Test
    void getOrderByBuyerId_InvalidId() {

        Assertions.assertThrows(UserNotFound.class, () -> {
                orderService.getOrdersByBuyerId(500);
        });
    }

    //getting order by id
    @Test
    void getOrderByValidOrderId() throws OrderNotFound {
        when(orderRepo.findById(1)).thenReturn(Optional.of(order));
        OrderDto actual = orderService.getOrderByOrderId(1);
        assertThat(actual).isEqualTo(orderDto);
    }

    @Test
    void getOrderByInvalidOrderId_negative() {
        Assertions.assertThrows(OrderNotFound.class, () -> {
            orderService.getOrderByOrderId(1);
        });
    }

    //create order
    @Test
    void createOrder_positive() {
        when(orderRepo.saveAndFlush(any(Order.class))).thenReturn(order);
        OrderDto actual = orderService.createOrder(orderDto);
        assertThat(actual).isEqualTo(orderDto);
    }

    //udpate order
    @Test
    void updateOrder_positive(){
        OrderDto editedOrder = new OrderDto();
        when(orderRepo.saveAndFlush(any(Order.class))).thenReturn(order);

        OrderDto actual = orderService.updateOrder(editedOrder);
        assertThat(actual).isEqualTo(orderDto);
    }
    //delete order
    @Test
    void deleteOrder_positive() throws OrderNotFound {
        when(orderRepo.findById(orderDto.getId())).thenReturn(Optional.of(order));
        assertThat(orderService.deleteOrder(orderDto)).isTrue();
    }

    @Test
    void deleteOrderThatDoesntExist_negative() {
        Assertions.assertThrows(OrderNotFound.class, ()-> {
           orderService.deleteOrder(orderDto);
        });
    }

}
