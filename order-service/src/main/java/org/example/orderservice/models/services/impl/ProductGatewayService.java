package org.example.orderservice.models.services.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.example.orderservice.clients.ProductClient;
import org.example.orderservice.exceptions.ProductNotFoundException;
import org.example.orderservice.exceptions.ProductServiceException;
import org.example.orderservice.models.dto.responses.ProductResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductGatewayService {

    private final ProductClient productClient;

    @CircuitBreaker(name = "productService", fallbackMethod = "getProductByIdFallback")
    public ProductResponse getProductById(Long productId) {
        try {
            return productClient.getProductById(productId);
        } catch (Exception e){
            getProductByIdFallback(productId, e);
            throw e;
        }
    }

    private Throwable getProductByIdFallback(Long productId, Throwable e) {
        if (e instanceof ProductServiceException) {
            throw new ProductServiceException("Product service lỗi hoặc không khả dụng") ;
        }
        throw new ProductNotFoundException(productId);
    }

}
