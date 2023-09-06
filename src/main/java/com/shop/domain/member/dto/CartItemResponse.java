package com.shop.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartItemResponse {
    private Long cartItemId;
    private Long amount;

    private Long itemId;
    private Long price;

    private Long productId;
    private String productName;

    private Long cost;
    private Long discountedCost;
}
