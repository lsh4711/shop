package com.shop.domain.member.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartResponse {
    private List<CartItemResponse> cartItems;
    private Long deliveryFee;
    private Long totalCost;
}
