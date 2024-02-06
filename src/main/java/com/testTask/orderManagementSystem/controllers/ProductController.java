package com.testTask.orderManagementSystem.controllers;

import com.testTask.orderManagementSystem.domain.Product;
import com.testTask.orderManagementSystem.dto.ProductDTO;
import com.testTask.orderManagementSystem.services.implementation.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class ProductController {

    private final ProductServiceImpl productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/add-product")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @DeleteMapping("/remove-product/{productId}")
    public ResponseEntity<Boolean> removeProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.removeProduct(productId));
    }
}
