package com.testTask.orderManagementSystem.services;

import com.testTask.orderManagementSystem.domain.Order;
import com.testTask.orderManagementSystem.domain.OrderItem;

import java.util.List;

public interface OrderService {
    Order placeOrder(Order order);
    void deleteNotPaidOrdersCreatedBeforeTimeout();
    Order payOrder(Long orderId);
    List<OrderItem> findOrderItemsForOrder(Long orderId);
}
