package com.testTask.orderManagementSystem.services;

import com.testTask.orderManagementSystem.domain.Order;

public interface OrderService {
    Order placeOrder(Order order);
    void deleteNotPaidOrdersCreatedBeforeTimeout();
    Order payOrder(Long orderId);
}
