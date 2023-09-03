package com.shop.domain.product.service;

import org.springframework.stereotype.Service;

import com.shop.domain.product.entity.ProductCategory;
import com.shop.domain.product.repository.ProductCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategory createProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}
