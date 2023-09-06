package com.shop.domain.coupon.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.domain.coupon.entity.Coupon.Info;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CouponResponse {
    private Long couponId;
    private String name;
    private Info info;
    private Long discountValue;
    private Long targetProductId;
}
