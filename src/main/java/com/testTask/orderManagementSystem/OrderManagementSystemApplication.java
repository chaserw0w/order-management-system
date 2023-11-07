package com.testTask.orderManagementSystem;

import com.testTask.orderManagementSystem.domain.Order;
import com.testTask.orderManagementSystem.domain.OrderItem;
import com.testTask.orderManagementSystem.domain.Product;
import com.testTask.orderManagementSystem.repo.OrderRepository;
import com.testTask.orderManagementSystem.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class OrderManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ProductRepository productRepository, OrderRepository orderRepository) {
		return args -> {
			Product product1 = productRepository.save(new Product(null, "IPhone 11", new BigDecimal(700), 30));
			Product product2 = productRepository.save(new Product(null, "IPhone 13", new BigDecimal(800), 70));
			Product product3 = productRepository.save(new Product(null, "IPhone 14", new BigDecimal(1000), 80));
			Product product4 = productRepository.save(new Product(null, "MacBook Air M1", new BigDecimal(1500), 55));
			Product product5 = productRepository.save(new Product(null, "MacBook Air M2", new BigDecimal(2000), 60));
			Product product6 = productRepository.save(new Product(null, "MacBook Pro M2", new BigDecimal(2500), 80));
			Product product7 = productRepository.save(new Product(null, "Apple Pro Stand", new BigDecimal(1000), 50));

			Order order1 = new Order();
			order1.setId(1L);
			order1.setOrderDate(new Date());
			order1.setPaid(false);

			OrderItem orderItem1 = new OrderItem();
			orderItem1.setId(1L);
			orderItem1.setProduct(product1);
			orderItem1.setQuantity(5);
			orderItem1.setOrder(order1);
			orderItem1.setItemTotal(product1.getPrice().multiply(BigDecimal.valueOf(5)));

			OrderItem orderItem2 = new OrderItem();
			orderItem2.setId(2L);
			orderItem2.setProduct(product2);
			orderItem2.setQuantity(10);
			orderItem2.setOrder(order1);
			orderItem2.setItemTotal(product2.getPrice().multiply(BigDecimal.valueOf(10)));
			BigDecimal totalCost = orderItem1.getItemTotal().add(orderItem2.getItemTotal());
			order1.setTotalCost(totalCost);

			order1.setOrderItems(List.of(orderItem1, orderItem2));

			orderRepository.save(order1);
		};
	}

}
