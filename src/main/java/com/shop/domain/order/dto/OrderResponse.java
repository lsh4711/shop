package com.shop.domain.order.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderResponse {
    private Long orderId;
    private String payment;
    private String status;
    private String createdAt;
    private String modifiedAt;

    // private List<CartItemResponse> items;

    private Long itemCost;
    private Long discounteditemCost;

    private Long deliveryCost; // 5만원 이상 구매 시 무료
    private Long discounteddeliveryCost; // 배송비 쿠폰도 추가

    private Long totalCost;
    private Long discountedtotalCost;
}
