package com.rupankar.productcommandservice.service;

import com.rupankar.productcommandservice.dto.ProductEvent;
import com.rupankar.productcommandservice.entity.Product;
import com.rupankar.productcommandservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductCommandService {

    private ProductRepository productRepository;

    private KafkaTemplate<String, Object> kafkaTemplate;

    public Product createProduct(ProductEvent productEvent){
        Product product = productEvent.getProduct();

        Product persistedProduct = productRepository.save(product);

        ProductEvent createProductEvent = new ProductEvent("CreateProduct", persistedProduct);
        kafkaTemplate.send("product-topic-event", createProductEvent);

        return persistedProduct;
    }

    public Product updateProduct(String productId, ProductEvent productEvent){

        Product existingProduct = productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Product not found with id :: "+productId));

        Product product = productEvent.getProduct();

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());

        Product persistedProduct = productRepository.save(existingProduct);
        ProductEvent updateProductEvent = new ProductEvent("UpdateProduct", persistedProduct);
        kafkaTemplate.send("product-topic-event", updateProductEvent);

        return persistedProduct;
    }
}
