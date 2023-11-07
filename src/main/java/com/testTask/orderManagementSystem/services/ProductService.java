package com.testTask.orderManagementSystem.services;

import com.testTask.orderManagementSystem.domain.Product;
import com.testTask.orderManagementSystem.dto.OrderDTO;
import com.testTask.orderManagementSystem.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    List<ProductDTO> getAllProducts();
}
