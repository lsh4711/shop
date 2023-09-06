package com.shop.domain.coupon.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.domain.coupon.dto.CouponResponse;
import com.shop.domain.coupon.entity.Coupon;
import com.shop.domain.coupon.mapper.CouponMapper;
import com.shop.domain.coupon.service.CouponService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupons")
public class CouponController {
    private final CouponService couponService;
    private final CouponMapper couponMapper;

    @GetMapping
    public ResponseEntity getCoupons() {
        List<Coupon> coupons = couponService.findCoupons();

        List<CouponResponse> couponResponses = couponMapper.couponsToCouponResponses(coupons);

        return ResponseEntity.ok(couponResponses);
    }
}
