package com.rupankar.productqueryservice.controller;

import com.rupankar.productqueryservice.entity.Product;
import com.rupankar.productqueryservice.service.ProductQueryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductQueryController {

    private ProductQueryService productQueryService;

    @GetMapping("/")
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok(productQueryService.getAllProducts());
    }
}
