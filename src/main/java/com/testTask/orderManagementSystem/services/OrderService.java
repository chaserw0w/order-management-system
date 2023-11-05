package com.testTask.orderManagementSystem.services;

import com.testTask.orderManagementSystem.dto.OrderDTO;

public interface OrderService {
    OrderDTO placeOrder(OrderDTO order);
}
