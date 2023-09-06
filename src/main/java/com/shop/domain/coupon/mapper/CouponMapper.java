package com.shop.domain.coupon.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.shop.domain.coupon.dto.CouponResponse;
import com.shop.domain.coupon.entity.Coupon;

@Mapper(componentModel = "spring")
public abstract class CouponMapper {
    public abstract List<CouponResponse> couponsToCouponResponses(List<Coupon> coupons);
}
