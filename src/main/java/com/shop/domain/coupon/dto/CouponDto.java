package com.shop.domain.coupon.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CouponDto {
    @NotEmpty
    @Size(max = 5, message = "쿠폰은 주문 당 최대 5개까지 사용 가능합니다.")
    private List<Long> couponIds;
}
