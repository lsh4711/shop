package com.shop.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
}
