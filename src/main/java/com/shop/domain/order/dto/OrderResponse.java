package com.shop.domain.order.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {
    private Long orderId;

    private Long deliveryFee;
    private Long totalCost;
    private Long discountedDeliveryFee;
    private Long discountedTotalCost;

    private String payment;
    private String status;

    private String createdAt;
    private String modifiedAt;

    private List<OrderItemResponse> orderitems;
}
