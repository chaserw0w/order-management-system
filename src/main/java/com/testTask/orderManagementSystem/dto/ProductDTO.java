package com.testTask.orderManagementSystem.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private int quantity;

}
