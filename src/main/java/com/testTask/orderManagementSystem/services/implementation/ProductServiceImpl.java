package com.testTask.orderManagementSystem.services.implementation;

import com.testTask.orderManagementSystem.domain.Product;
import com.testTask.orderManagementSystem.dto.ProductDTO;
import com.testTask.orderManagementSystem.repo.OrderRepository;
import com.testTask.orderManagementSystem.repo.ProductRepository;
import com.testTask.orderManagementSystem.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> availableProducts = productRepository.findAll();

        return availableProducts.stream()
                .map(product -> {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setId(product.getId());
                    productDTO.setName(product.getName());
                    productDTO.setPrice(product.getPrice());
                    productDTO.setQuantity(product.getQuantity());
                    return productDTO;
                })
                .collect(Collectors.toList());
    }
}
