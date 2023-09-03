package com.shop.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
