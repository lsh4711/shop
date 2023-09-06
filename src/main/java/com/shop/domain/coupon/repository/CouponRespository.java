package com.shop.domain.coupon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.coupon.entity.Coupon;

public interface CouponRespository extends JpaRepository<Coupon, Long> {
    List<Coupon> findAllByMember_MemberId(long memberId);

    List<Coupon> findAllByCouponIdInAndMember_MemberId(List<Long> couponIds, long memberId);
}
