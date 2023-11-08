package com.testTask.orderManagementSystem.services.implementation;

import com.testTask.orderManagementSystem.domain.Product;
import com.testTask.orderManagementSystem.dto.ProductDTO;
import com.testTask.orderManagementSystem.repo.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository productRepository;
    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product(1L, "Product1", new BigDecimal("10.0"), 100);
        Product product2 = new Product(2L, "Product2", new BigDecimal("20.0"), 50);

        Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));
        List<ProductDTO> productDTOs = productService.getAllProducts();
        assertEquals(2, productDTOs.size());

        ProductDTO productDTO1 = productDTOs.get(0);
        assertEquals(product1.getId(), productDTO1.getId());
        assertEquals(product1.getName(), productDTO1.getName());
        assertEquals(product1.getPrice(), productDTO1.getPrice());
        assertEquals(product1.getQuantity(), productDTO1.getQuantity());

        ProductDTO productDTO2 = productDTOs.get(1);
        assertEquals(product2.getId(), productDTO2.getId());
        assertEquals(product2.getName(), productDTO2.getName());
        assertEquals(product2.getPrice(), productDTO2.getPrice());
        assertEquals(product2.getQuantity(), productDTO2.getQuantity());
    }

    @Test
    public void testSaveProduct() {
        Product productToSave = new Product(1L, "Sample Product", new BigDecimal("10.0"), 100);

        Mockito.when(productRepository.save(productToSave)).thenReturn(productToSave);
        Product savedProduct = productService.saveProduct(productToSave);

        assertEquals(productToSave.getId(), savedProduct.getId());
        assertEquals(productToSave.getName(), savedProduct.getName());
        assertEquals(productToSave.getPrice(), savedProduct.getPrice());
        assertEquals(productToSave.getQuantity(), savedProduct.getQuantity());
    }
}
