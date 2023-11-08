package com.testTask.orderManagementSystem.services.implementation;

import com.testTask.orderManagementSystem.domain.Order;
import com.testTask.orderManagementSystem.domain.OrderItem;
import com.testTask.orderManagementSystem.domain.Product;
import com.testTask.orderManagementSystem.repo.OrderRepository;
import com.testTask.orderManagementSystem.repo.ProductRepository;
import com.testTask.orderManagementSystem.util.OrderNotFoundException;
import com.testTask.orderManagementSystem.util.OrderPaymentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;
    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPlaceOrder() {
        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        Product product = new Product();
        product.setId(1L);
        product.setPrice(new BigDecimal("10.0"));
        orderItem.setProduct(product);
        orderItem.setQuantity(2);
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        order.setOrderItems(orderItems);

        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(product));

        Order resultOrder = orderService.placeOrder(order);
        Mockito.verify(orderRepository, Mockito.times(1)).save(order);

        OrderItem resultOrderItem = resultOrder.getOrderItems().get(0);
        BigDecimal expectedTotalCost = new BigDecimal("20.0");
        assertEquals(expectedTotalCost, resultOrder.getTotalCost());
        assertEquals(product, resultOrderItem.getProduct());
        assertEquals(expectedTotalCost, resultOrderItem.getItemTotal());
    }

    @Test
    public void testDeleteNotPaidOrdersCreatedBeforeTimeout() {
        List<Order> orders = new ArrayList<>();
        Order order1 = new Order();
        order1.setId(1L);
        order1.setOrderDate(new Date());
        orders.add(order1);

        Mockito.when(orderRepository.findNotPaidOrdersCreatedBeforeTimeOut(Mockito.any(Date.class))).thenReturn(orders);
        orderService.deleteNotPaidOrdersCreatedBeforeTimeout();
        Mockito.verify(orderRepository, Mockito.times(1)).deleteNotPaidOrdersCreatedBeforeTimeout(Mockito.any(Date.class));
    }

    @Test
    public void testLogDeletedOrders() {
        List<Order> orders = new ArrayList<>();
        Order order1 = new Order();
        order1.setId(1L);
        order1.setOrderDate(new Date());
        order1.setTotalCost(new BigDecimal("50.0"));
        orders.add(order1);

        Mockito.when(orderRepository.findNotPaidOrdersCreatedBeforeTimeOut(Mockito.any(Date.class))).thenReturn(orders);
        Date date = new Date();
        orderService.logDeletedOrders(date);
        Mockito.verify(orderRepository, Mockito.times(1)).findNotPaidOrdersCreatedBeforeTimeOut(Mockito.any(Date.class));
    }

    @Test
    public void testPayOrder_Success() {
        Order order = new Order();
        order.setId(1L);
        order.setPaid(false);

        Mockito.when(orderRepository.findById(1L)).thenReturn(java.util.Optional.of(order));
        Order paidOrder = orderService.payOrder(1L);
        Mockito.verify(orderRepository, Mockito.times(1)).save(order);
        assertTrue(paidOrder.isPaid());
    }

    @Test
    public void testPayOrder_OrderNotFound() {
        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> {
            orderService.payOrder(1L);
        });
    }

    @Test
    public void testPayOrder_AlreadyPaid() {
        Order order = new Order();
        order.setId(1L);
        order.setPaid(true);

        Mockito.when(orderRepository.findById(1L)).thenReturn(java.util.Optional.of(order));
        assertThrows(OrderPaymentException.class, () -> {
            orderService.payOrder(1L);
        });
    }
}
