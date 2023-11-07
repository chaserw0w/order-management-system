package com.testTask.orderManagementSystem.services.implementation;

import com.testTask.orderManagementSystem.domain.Order;
import com.testTask.orderManagementSystem.domain.OrderItem;
import com.testTask.orderManagementSystem.domain.Product;
import com.testTask.orderManagementSystem.repo.OrderRepository;
import com.testTask.orderManagementSystem.repo.ProductRepository;
import com.testTask.orderManagementSystem.services.OrderService;
import com.testTask.orderManagementSystem.util.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order placeOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null!");
        }

        order.setOrderDate(new Date());
        order.setPaid(false);

        BigDecimal totalCost = BigDecimal.ZERO;

        if (order.getOrderItems() != null) {
            for (OrderItem orderItem : order.getOrderItems()) {
                if (orderItem.getProduct() != null && orderItem.getProduct().getId() != null) {
                    Product product = productRepository.findById(orderItem.getProduct().getId())
                            .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + orderItem.getProduct().getId()));
                    int quantity = orderItem.getQuantity();
                    BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(quantity));
                    totalCost = totalCost.add(itemTotal);
                    orderItem.setItemTotal(itemTotal);
                    orderItem.setProduct(product);
                    orderItem.setOrder(order);
                }
            }
        }
        order.setTotalCost(totalCost);

        return orderRepository.save(order);
    }
}
