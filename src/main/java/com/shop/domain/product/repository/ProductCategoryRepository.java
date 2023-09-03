package com.shop.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.product.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
