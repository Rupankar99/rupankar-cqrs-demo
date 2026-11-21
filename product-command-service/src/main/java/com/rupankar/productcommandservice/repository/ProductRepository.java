package com.rupankar.productcommandservice.repository;

import com.rupankar.productcommandservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
