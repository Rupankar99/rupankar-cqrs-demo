package com.rupankar.productqueryservice.service;

import com.rupankar.productqueryservice.dto.ProductEvent;
import com.rupankar.productqueryservice.entity.Product;
import com.rupankar.productqueryservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaEventHandler {

    private final ProductRepository productRepository;

    @KafkaListener(topics = "product-topic-event", groupId = "product-event-groups")
    public void processProductEvents(ProductEvent productEvent){

        String eventType = productEvent.getEventType();
        log.info("Event arrived with type :: {}", eventType);
        switch(eventType){
            case "CreateProduct":
                processCreateProductEvent(productEvent);
                break;
            case "UpdateProduct":
                processUpdateProductEvent(productEvent);
                break;
        }
    }

    private void processCreateProductEvent(ProductEvent productEvent) {

        Product product = productEvent.getProduct();
        log.info("Product id :: {}", product.getId());
        productRepository.save(product);
    }

    private void processUpdateProductEvent(ProductEvent productEvent) {

        Product product = productEvent.getProduct();

        String id = product.getId();

        Product exisitingProduct = productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No product found with id :: "+id));

        exisitingProduct.setName(product.getName());
        exisitingProduct.setDescription(product.getDescription());
        exisitingProduct.setPrice(product.getPrice());

        productRepository.save(exisitingProduct);
    }
}
