package com.shop.domain.category.mapper;

import java.util.List;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import com.shop.domain.category.dto.CategoryDto;
import com.shop.domain.category.dto.CategoryResponse;
import com.shop.domain.category.entity.Category;
import com.shop.domain.category.service.CategoryService;
import com.shop.domain.product.entity.ProductCategory;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {
    @Autowired
    private CategoryService categoryService;

    @BeforeMapping
    void verifyPostDto(CategoryDto.Post postDto, @MappingTarget Category category) {
        categoryService.verifyCategoryByName(postDto.getName());
    }

    public abstract Category postDtoToCategory(CategoryDto.Post postDto);

    public abstract List<CategoryResponse> categoriesToCategoryResponses(List<Category> categories);

    @Mapping(target = "categoryId", source = "category.categoryId")
    @Mapping(target = "name", source = "category.name")
    public abstract CategoryResponse productCategoryToCategoryResponse(
            ProductCategory productCategory);

    public abstract List<CategoryResponse> productCategoriesToCategoryResponses(
            List<ProductCategory> productCategories);
}
