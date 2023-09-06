package com.shop.domain.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.domain.coupon.dto.CouponDto;

@RestController
@RequestMapping("/api/orders/pay")
public class OrderController {
    @PostMapping
    public ResponseEntity postOrder(@RequestBody CouponDto.Patch couponPatchDto) {

        return null;
    }
}
