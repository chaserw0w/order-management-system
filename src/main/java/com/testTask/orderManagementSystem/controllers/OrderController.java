package com.testTask.orderManagementSystem.controllers;

import com.testTask.orderManagementSystem.domain.Order;
import com.testTask.orderManagementSystem.domain.OrderItem;
import com.testTask.orderManagementSystem.services.implementation.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/orders")
public class OrderController {
    private final OrderServiceImpl orderService;

    @PostMapping("/place-order")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.placeOrder(order));
    }

    @PostMapping("/order-pay/{orderId}")
    public ResponseEntity<Order> payOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.payOrder(orderId));
    }

    @GetMapping("/{orderId}/orderItems")
    public ResponseEntity<List<OrderItem>> findOrderItemsForOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.findOrderItemsForOrder(orderId));
    }
}
