package com.testTask.orderManagementSystem.controllers;

import com.testTask.orderManagementSystem.domain.Order;
import com.testTask.orderManagementSystem.services.implementation.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/orders")
public class OrderController {
    private final OrderServiceImpl orderService;

    @PostMapping("/place-order")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        Order placedOrder = orderService.placeOrder(order);
        return ResponseEntity.ok(placedOrder);
    }
}
