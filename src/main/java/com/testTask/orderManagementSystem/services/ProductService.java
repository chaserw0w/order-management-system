package com.testTask.orderManagementSystem.services;

import com.testTask.orderManagementSystem.dto.OrderDTO;
import com.testTask.orderManagementSystem.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
}
