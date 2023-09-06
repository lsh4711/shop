package com.shop.domain.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemResponse {
    private Long orderItemId;

    // Product
    private Long productId;

    private String productName;

    // CartItem
    private Long amount;

    // Item
    private Long itemId;

    private Long price;

    // CartItemResponse
    private Long cost;

    private Long discountedCost;
}
