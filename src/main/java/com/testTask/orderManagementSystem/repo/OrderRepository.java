package com.testTask.orderManagementSystem.repo;

import com.testTask.orderManagementSystem.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
