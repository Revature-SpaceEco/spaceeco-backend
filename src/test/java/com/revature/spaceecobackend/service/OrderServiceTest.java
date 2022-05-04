package com.revature.spaceecobackend.service;

import com.revature.spaceecobackend.dao.OrderRepository;
import com.revature.spaceecobackend.dao.UserRepository;
import com.revature.spaceecobackend.dto.*;
import com.revature.spaceecobackend.exception.EmptyFields;
import com.revature.spaceecobackend.exception.NotFound;
import com.revature.spaceecobackend.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderRepository orderRepo;

    @Mock
    private UserRepository userRepo;

    @Mock
    private AddressService addressService;

    @Mock
    private PaymentService paymentService;

    @Spy
    ModelMapper modelMapper;

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
    private static BillingDetails billingDetails;
    private static Timestamp orderDate;
    private static UserDTO buyerDTO;
    private static ProductDto productDto1;
    private static ProductDto productDto2;
    private static List<ProductDto> productDtos;

    @BeforeEach
    public void init() {
        ModelMapper modelMapper = new ModelMapper();
        billingDetails = new BillingDetails();
        userRole2 = new UserRole(2, "seller");
        seller = new User(2, "seller", "password", "email@email.com", "tester", "testy", userRole2, address, billingDetails, "www.image.com", true, "secret");
        category = new Categories(1, "categoryTest");
        products = new ArrayList<>();
        products.add(new Product(1, "test", "test description", 100, category, "image.jpg", seller));
        products.add(new Product(2, "test", "test description", 100, category, "image2.jpg", seller));
        productDto1 = modelMapper.map(products.get(0), ProductDto.class);
        productDto2 = modelMapper.map(products.get(1), ProductDto.class);
        productDtos = new ArrayList<>();
        productDtos.add(productDto1);
        productDtos.add(productDto2);
        payment = new Payment();
        userRole = new UserRole(1, "buyer");
        buyer = new User(1, "test", "password", "email@email.com", "tester", "testy", userRole, address, billingDetails, "www.image.com", true, "secret");
        buyerDTO = modelMapper.map(buyer, UserDTO.class);
        address = new Address(1, "10 Test Drive", null, "City Test", "Test", "Country", "80000", "Solar", "Planet");
        orders = new ArrayList<>();
        orderDate = new Timestamp(System.currentTimeMillis());
        order = new Order(1, buyer, products, orderDate, "pending", address, payment);
        orders.add(order);
        paymentDto = new PaymentDto();
        orderDtos = new ArrayList<>();
        orderDto = new OrderDto(1, buyerDTO, productDtos, orderDate, "pending", address, paymentDto);
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
    void getOrderByBuyerId_ValidId() throws NotFound {
        when(userRepo.findById(1)).thenReturn(Optional.of(buyer));
        when(orderRepo.findByBuyerId(1)).thenReturn(orders);

        List<OrderDto> actual = orderService.getOrdersByBuyerId(1);
        assertThat(actual).isEqualTo(orderDtos);
    }

    @Test
    void getOrderByBuyerId_InvalidId() {

        Assertions.assertThrows(NotFound.class, () -> {
            orderService.getOrdersByBuyerId(500);
        });
    }

    //getting order by id
    @Test
    void getOrderByValidOrderId() throws NotFound {
        when(orderRepo.findById(1)).thenReturn(Optional.of(order));
        OrderDto actual = orderService.getOrderByOrderId(1);
        assertThat(actual).isEqualTo(orderDto);
    }

    @Test
    void getOrderByInvalidOrderId_negative() {
        Assertions.assertThrows(NotFound.class, () -> {
            orderService.getOrderByOrderId(1);
        });
    }

    //create order
    @Test
    void createOrder_positive() throws EmptyFields {
        when(orderRepo.saveAndFlush(any(Order.class))).thenReturn(order);
        AddressDTO addressDto = modelMapper.map(orderDto.getShippingAddressId(), AddressDTO.class);
        when(modelMapper.map(orderDto, Order.class)).thenReturn(order);
        when(modelMapper.map(orderDto.getShippingAddressId(), AddressDTO.class)).thenReturn(addressDto);
        when(addressService.createAddressOrder(addressDto)).thenReturn(address);
        when(paymentService.createPayment(orderDto.getPayment())).thenReturn(payment);
        OrderDto actual = orderService.createOrder(orderDto);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        actual.setOrderDate(currentTime);
        orderDto.setOrderDate(currentTime);
        assertThat(actual).isEqualTo(orderDto);
    }

    //update order
    @Test
    void updateOrder_positive() throws NotFound, EmptyFields {
        OrderDto editedOrder = new OrderDto(1, buyerDTO, productDtos, orderDate, "pending", address, paymentDto);
        when(orderRepo.findById(editedOrder.getId())).thenReturn(Optional.of(order));
        when(orderRepo.saveAndFlush(any(Order.class))).thenReturn(order);

        OrderDto actual = orderService.updateOrder(editedOrder);
        assertThat(actual).isEqualTo(orderDto);
    }


    @Test
    void createOrder_EmptyFields() {
        Assertions.assertThrows(EmptyFields.class, () -> {
            orderService.createOrder(new OrderDto());
        });
    }

    @Test
    void updateOrder_NotFound() {
        Assertions.assertThrows(NotFound.class, () -> {
            orderService.updateOrder(orderDto);
        });
    }

    @Test
    void updateOrder_EmptyFields() {
        Assertions.assertThrows(EmptyFields.class, () -> {
            orderService.updateOrder(new OrderDto());
        });
    }

    //delete order
    @Test
    void deleteOrder_positive() throws NotFound {
        when(orderRepo.findById(orderDto.getId())).thenReturn(Optional.of(order));
        assertThat(orderService.deleteOrder(orderDto)).isTrue();
    }

    @Test
    void deleteOrderThatDoesntExist_negative() {
        Assertions.assertThrows(NotFound.class, () -> {
            orderService.deleteOrder(orderDto);
        });
    }

}
