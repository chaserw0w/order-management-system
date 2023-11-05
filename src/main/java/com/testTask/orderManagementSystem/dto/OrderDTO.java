package com.testTask.orderManagementSystem.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class OrderDTO {

    private Long id;
    private Date orderDate;
    private List<OrderItemDTO> orderItems;
    private boolean paid;
}
