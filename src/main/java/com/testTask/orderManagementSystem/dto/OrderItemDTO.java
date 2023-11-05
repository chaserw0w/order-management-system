package com.testTask.orderManagementSystem.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
@Setter
public class OrderItemDTO {
    private Long id;
    private ProductDTO product;
    private int quantity;
    private BigDecimal itemTotal;
}
