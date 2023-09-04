package com.shop.domain.category.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.shop.domain.category.dto.CategoryResponse;
import com.shop.domain.product.entity.ProductCategory;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "categoryId", source = "category.categoryId")
    @Mapping(target = "name", source = "category.name")
    public abstract CategoryResponse productCategoryToCategoryResponse(
            ProductCategory productCategory);

    public abstract List<CategoryResponse> productCategoriesToCategoryResponses(
            List<ProductCategory> productCategories);
}
