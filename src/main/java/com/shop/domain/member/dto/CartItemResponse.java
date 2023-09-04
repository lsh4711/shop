package com.shop.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItemResponse {
    private Long cartItemId;
    private Long amount;

    private Long itemId;

    private String productName;
}
