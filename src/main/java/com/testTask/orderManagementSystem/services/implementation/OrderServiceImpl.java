package com.testTask.orderManagementSystem.services.implementation;

import com.testTask.orderManagementSystem.domain.Order;
import com.testTask.orderManagementSystem.domain.OrderItem;
import com.testTask.orderManagementSystem.domain.Product;
import com.testTask.orderManagementSystem.repo.OrderRepository;
import com.testTask.orderManagementSystem.repo.ProductRepository;
import com.testTask.orderManagementSystem.services.OrderService;
import com.testTask.orderManagementSystem.util.OrderNotFoundException;
import com.testTask.orderManagementSystem.util.OrderPaymentException;
import com.testTask.orderManagementSystem.util.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        Optional.ofNullable(order).orElseThrow(() -> new IllegalArgumentException("The 'order' parameter cannot be null!"));
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

    @Scheduled(fixedRate = 600_000)
    @Transactional
    @Override
    public void deleteNotPaidOrdersCreatedBeforeTimeout() {
        LocalDateTime localDateTime = LocalDateTime.now().minusMinutes(10);
        Date tenMinutesAge = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        orderRepository.deleteNotPaidOrdersCreatedBeforeTimeout(tenMinutesAge);

        logDeletedOrders(tenMinutesAge);
    }

    //was made default in order to be accessible from tests
    void logDeletedOrders(Date date) {
        List<Order> deletedOrders = orderRepository.findNotPaidOrdersCreatedBeforeTimeOut(date);
        for (Order order : deletedOrders) {
            log.info("Deleted order: ID={}, Order Date={}, Total Cost={}", order.getId(), order.getOrderDate(), order.getTotalCost());
        }
        log.info("Deletion completed. Deleted {} orders.", deletedOrders.size());
    }

    @Override
    @Transactional
    public Order payOrder(Long orderId) {
        try {
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));

            if (order.isPaid()) {
                throw new OrderPaymentException("Order has already been paid!");
            }
            order.setPaid(true);
            orderRepository.save(order);
            log.info("Order with id {} has been paid.", orderId);
            return order;
        } catch (OrderNotFoundException onfe) {
            log.error("Error while processing order payment: {}", onfe.getMessage(), onfe);
            throw onfe;
        } catch (Exception e) {
            log.error("Error while processing order payment: {}", e.getMessage(), e);
            throw new OrderPaymentException("Error processing order payment");
        }
    }

    @Override
    public List<OrderItem> findOrderItemsForOrder(Long orderId) {
        return null;
    }
}
