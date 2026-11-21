package com.rupankar.productqueryservice.service;

import com.rupankar.productqueryservice.entity.Product;
import com.rupankar.productqueryservice.repository.ProductRepository;
import lombok.AllArgsConstructor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductQueryService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
