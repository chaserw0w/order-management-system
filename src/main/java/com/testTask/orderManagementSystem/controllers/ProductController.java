package com.testTask.orderManagementSystem.controllers;

import com.testTask.orderManagementSystem.domain.Product;
import com.testTask.orderManagementSystem.dto.ProductDTO;
import com.testTask.orderManagementSystem.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/user")
    public String getUser() {
        return "Fuck Spring Security";
    }

    @GetMapping("/public")
    public String getPublicUser() {
        return "Fuck Public Spring Security";
    }

    @GetMapping("/secured")
    public String getSecurdUser() {
        return "Fuck Secured Spring Security";
    }

    /*@GetMapping("/goods")
    public List<Product> getAllgoods() {

    }*/
}
