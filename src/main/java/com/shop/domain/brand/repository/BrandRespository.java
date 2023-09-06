package com.shop.domain.brand.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.brand.entity.Brand;

public interface BrandRespository extends JpaRepository<Brand, Long> {
    boolean existsByName(String name);
}
