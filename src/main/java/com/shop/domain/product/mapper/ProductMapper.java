package com.shop.domain.product.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import com.shop.domain.brand.service.BrandService;
import com.shop.domain.category.entity.Category;
import com.shop.domain.category.service.CategoryService;
import com.shop.domain.product.dto.ProductDto;
import com.shop.domain.product.entity.Product;
import com.shop.domain.product.entity.ProductCategory;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @BeforeMapping
    void verifyPostDto(ProductDto.Post postDto, @MappingTarget Product product) {
        brandService.findBrand(postDto.getBrandId());

        List<ProductCategory> productCategories = new ArrayList<>();

        for (long categoryId : postDto.getCategoryIds()) {
            Category category = categoryService.findCategory(categoryId);
            productCategories.add(new ProductCategory(null, product, category));
        }
        product.setProductCategories(productCategories);
    }

    
    @Mapping(target = "brand.brandId", source = "brandId")
    public abstract Product postDtoToProduct(ProductDto.Post postDto);
}
