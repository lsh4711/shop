package com.shop.domain.product.dto;

import java.util.List;

import com.shop.domain.category.dto.CategoryResponse;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResponse {
    private Long productId;
    private String productName;
    private String code;

    // Category
    private List<CategoryResponse> categories;

    // Brand
    private Long brandId;
    private String brandName;
}
