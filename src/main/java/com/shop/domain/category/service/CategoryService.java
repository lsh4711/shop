package com.shop.domain.category.service;

import org.springframework.stereotype.Service;

import com.shop.domain.category.entity.Category;
import com.shop.domain.category.repository.CategoryRepository;
import com.shop.global.exception.CustomException;
import com.shop.global.exception.ExceptionCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category creatCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category findCategory(long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomException(ExceptionCode.CATEGORY_NOT_FOUND));
    }
}
