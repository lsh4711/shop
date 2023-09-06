package com.shop.domain.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderListResponse {
    private Long orderId;

    private Long deliveryFee;
    private Long totalCost;
    private Long discountedDeliveryFee;
    private Long discountedTotalCost;

    private String payment;
    private String status;

    private String createdAt;
    private String modifiedAt;
}
