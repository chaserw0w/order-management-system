package com.testTask.orderManagementSystem.repo;

import com.testTask.orderManagementSystem.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Modifying
    @Query("DELETE FROM Order o WHERE o.paid = false AND o.orderDate < :tenMinutesAgo")
    void deleteNotPaidOrdersCreatedBeforeTimeout(@Param("tenMinutesAgo") Date tenMinutesAge);

    @Query("SELECT o FROM Order o WHERE o.paid = false AND o.orderDate <= :date")
    List<Order> findNotPaidOrdersCreatedBeforeTimeOut(Date date);
}
