package com.shop.domain.member.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartResponse {
    private List<CartItemResponse> cartItems;
    private Long deliveryFee;
    private Long totalCost;
    private Long discountedDeliveryFee;
    private Long discountedTotalCost;
}
