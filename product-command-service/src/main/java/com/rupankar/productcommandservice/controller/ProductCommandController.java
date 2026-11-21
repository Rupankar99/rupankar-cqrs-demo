package com.rupankar.productcommandservice.controller;

import com.rupankar.productcommandservice.dto.ProductEvent;
import com.rupankar.productcommandservice.entity.Product;
import com.rupankar.productcommandservice.service.ProductCommandService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductCommandController {

    private ProductCommandService productCommandService;

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody ProductEvent productEvent){
        return ResponseEntity.ok(productCommandService.createProduct(productEvent));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id")String id ,@RequestBody ProductEvent productEvent){
        return ResponseEntity.ok(productCommandService.updateProduct(id,productEvent));
    }
}
