package com.testTask.orderManagementSystem.controllers;

import com.testTask.orderManagementSystem.dto.ProductDTO;
import com.testTask.orderManagementSystem.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @GetMapping("/hello")
    public String hello() {
        return "Fuck Spring Security";
    }
}
