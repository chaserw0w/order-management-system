package com.testTask.orderManagementSystem.services.implementation;

import com.testTask.orderManagementSystem.domain.Order;
import com.testTask.orderManagementSystem.domain.OrderItem;
import com.testTask.orderManagementSystem.domain.Product;
import com.testTask.orderManagementSystem.dto.OrderDTO;
import com.testTask.orderManagementSystem.dto.OrderItemDTO;
import com.testTask.orderManagementSystem.repo.OrderRepository;
import com.testTask.orderManagementSystem.repo.ProductRepository;
import com.testTask.orderManagementSystem.services.OrderService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OrderDTO placeOrder(OrderDTO order) {
        Order newOrder = new Order();
        newOrder.setOrderDate(new Date());

        List<OrderItemDTO> items = order.getOrderItems();
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDTO item : items) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found!"));

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(item.getQuantity());
            orderItem.setOrder(newOrder);

            orderItems.add(orderItem);
        }
        newOrder.setOrderItems(orderItems);
        newOrder.setPaid(false);    //Initially the order is not paid

        Order savedOrder = orderRepository.save(newOrder);
        OrderDTO savedOrderDTO = new OrderDTO();

        return savedOrderDTO;
    }
}
