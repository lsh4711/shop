package com.shop.domain.category.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.domain.category.dto.CategoryDto;
import com.shop.domain.category.dto.CategoryResponse;
import com.shop.domain.category.entity.Category;
import com.shop.domain.category.mapper.CategoryMapper;
import com.shop.domain.category.service.CategoryService;
import com.shop.global.utils.UriCreator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping("/register")
    public ResponseEntity postCategory(@Valid @RequestBody CategoryDto.Post postDto) {
        Category category = categoryMapper.postDtoToCategory(postDto);
        Category savedCategory = categoryService.createCategory(category);

        URI location = UriCreator.createUri("/api/categories", savedCategory.getCategoryId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity getCategories() {
        List<Category> categories = categoryService.findCategories();

        List<CategoryResponse> categoryResponses = categoryMapper
                .categoriesToCategoryResponses(categories);

        return ResponseEntity.ok(categoryResponses);
    }
}
