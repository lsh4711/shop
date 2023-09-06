package com.shop.domain.coupon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.domain.coupon.entity.Coupon;
import com.shop.domain.coupon.repository.CouponRespository;
import com.shop.global.exception.CustomException;
import com.shop.global.exception.ExceptionCode;
import com.shop.global.utils.AuthUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRespository couponRespository;

    public Coupon createCoupon(Coupon coupon) {
        return couponRespository.save(coupon);
    }

    public List<Coupon> findCoupons() {
        return couponRespository.findAllByMember_MemberId(AuthUtils.getMemberId());
    }

    public List<Coupon> findCouponsByCouponIdsAndAuthId(List<Long> couponIds) {
        List<Coupon> coupons = couponRespository.findAllByCouponIdInAndMember_MemberId(
            couponIds, AuthUtils.getMemberId());

        if (coupons.size() != couponIds.size()) {
            throw new CustomException(ExceptionCode.COUPON_NOT_FOUND);
        }

        return coupons;
    }

    public void deleteCouponsByCouponIdsAndAuthId(List<Long> couponIds) {
        couponRespository.deleteAllById(couponIds);
    }
}
