package com.shop.domain.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.domain.product.entity.Product;
import com.shop.domain.product.repository.ProductRepository;
import com.shop.global.exception.CustomException;
import com.shop.global.exception.ExceptionCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product findProduct(long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(ExceptionCode.PRODUCT_NOT_FOUND));
    }

    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    public void verifyBarcode(String barcode) {
        boolean exists = productRepository.existsByBarcode(barcode);

        if (exists) {
            throw new CustomException(ExceptionCode.PRODUCT_EXISTS);
        }

    }
}
