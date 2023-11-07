package com.testTask.orderManagementSystem;

import com.testTask.orderManagementSystem.domain.Product;
import com.testTask.orderManagementSystem.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class OrderManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ProductRepository productRepository) {
		return args -> {
			productRepository.save(new Product(null, "IPhone 11", new BigDecimal(700), 30));
			productRepository.save(new Product(null, "IPhone 13", new BigDecimal(800), 70));
			productRepository.save(new Product(null, "IPhone 14", new BigDecimal(1000), 80));
			productRepository.save(new Product(null, "MacBook Air M1", new BigDecimal(1500), 55));
			productRepository.save(new Product(null, "MacBook Air M2", new BigDecimal(2000), 60));
			productRepository.save(new Product(null, "MacBook Pro M2", new BigDecimal(2500), 80));
			productRepository.save(new Product(null, "Apple Pro Stan", new BigDecimal(1000), 50));
		};
	}

}
