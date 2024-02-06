package com.testTask.orderManagementSystem.services.implementation;

import com.testTask.orderManagementSystem.domain.Product;
import com.testTask.orderManagementSystem.dto.ProductDTO;
import com.testTask.orderManagementSystem.repo.ProductRepository;
import com.testTask.orderManagementSystem.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.*;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> availableProducts = productRepository.findAll();
        log.info("Listing all of the available products: {}", availableProducts.toString());

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

    @Override
    public Product saveProduct(Product product) {
        log.info("Saving new product: {}", product.getName());
        return productRepository.save(product);
    }

    @Override
    public Boolean removeProduct(Long id) {
        log.info("Deleting product by Id: {}", id);
        productRepository.deleteById(id);
        return TRUE;
    }
}
