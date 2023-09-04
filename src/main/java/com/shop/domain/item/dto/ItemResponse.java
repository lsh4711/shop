package com.shop.domain.item.dto;

import java.util.List;

import com.shop.domain.category.dto.CategoryResponse;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemResponse {
    // Item
    private Long itemId;
    private Long price;
    private String createdAt;
    private String modifiedAt;

    // Mart
    private Long martId;
    private String martName;

    // Product
    private Long productId;
    private String productName;
    private String code;

    // Category
    private List<CategoryResponse> categories;

    // Brand
    private Long brandId;
    private String brandName;
}
