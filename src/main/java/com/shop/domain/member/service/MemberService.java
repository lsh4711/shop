package com.shop.domain.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shop.domain.coupon.entity.Coupon;
import com.shop.domain.coupon.service.CouponService;
import com.shop.domain.member.dto.CartItemResponse;
import com.shop.domain.member.entity.CartItem;
import com.shop.domain.member.entity.Member;
import com.shop.domain.member.repository.CartItemRepository;
import com.shop.domain.member.repository.MemberRepository;
import com.shop.global.exception.CustomException;
import com.shop.global.exception.ExceptionCode;
import com.shop.global.utils.AuthUtils;
import com.shop.global.utils.CustomBeanUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final CartItemRepository cartItemRepository;

    private final CouponService couponService;

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Member findMember(long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    public Member findMemberByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    public void deleteMember() {
        memberRepository.deleteById(AuthUtils.getMemberId());
    }

    public void verifyUsername(String username) {
        boolean exists = memberRepository.existsByUsername(username);

        if (exists) {
            throw new CustomException(ExceptionCode.MEMBER_EXISTS);
        }

    }

    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    // CartItem

    public CartItem addCartItem(CartItem cartItem) {
        CartItem foundCartItem = cartItemRepository.findByItem_ItemIdAndMember_MemberId(
            cartItem.getItem().getItemId(), AuthUtils.getMemberId()).orElseGet(() -> {
                CartItem newCartItem = new CartItem();
                newCartItem.setMember(cartItem.getMember());
                newCartItem.setItem(cartItem.getItem());
                newCartItem.setAmount(0L);
                return newCartItem;
            });

        foundCartItem.setAmount(foundCartItem.getAmount() + cartItem.getAmount());

        return cartItemRepository.save(foundCartItem);
    }

    public CartItem updateCartItemInfo(CartItem cartItem) {
        CartItem foundCartItem = findCartItemByCartItemIdAndAuthId(cartItem.getCartItemId());

        CustomBeanUtils.copyNonNullProperties(cartItem, foundCartItem);

        return cartItemRepository.save(foundCartItem);
    }

    public CartItem findCartItemByCartItemIdAndAuthId(long cartItemId) {
        return cartItemRepository
                .findByCartItemIdAndMember_MemberId(cartItemId, AuthUtils.getMemberId())
                .orElseThrow(() -> new CustomException(ExceptionCode.CART_ITEM_NOT_FOUND));
    }

    public List<CartItem> findCartItems() {
        return cartItemRepository
                .findAllByMember_MemberId(AuthUtils.getMemberId());
    }

    public void deleteCartItem(long cartItemId) {
        findCartItemByCartItemIdAndAuthId(cartItemId);
        cartItemRepository.deleteById(cartItemId);
    }

    public void deleteCartItems() {
        cartItemRepository.deleteAllByMember_MemberId(AuthUtils.getMemberId());
    }

    // Coupon

    public int discountCartItems(List<CartItemResponse> responses,
            List<Long> couponIds) {
        List<Coupon> coupons = couponService.findCouponsByCouponIdsAndAuthId(couponIds);

        Map<Long, int[]> map = new HashMap<>(); // int[1] = fixEach, int[2] = rateEach
        int[] discountValuesForAll = new int[5]; // [3] = rateAll, [4] = fixAll

        calculateDiscountValues(coupons, map, discountValuesForAll);
        calculateDiscountedCost(responses, map, discountValuesForAll);

        return discountValuesForAll[4];
    }

    private void calculateDiscountValues(List<Coupon> coupons,
            Map<Long, int[]> map, int[] discountValuesForAll) {
        for (Coupon coupon : coupons) {
            int order = coupon.getInfo().getOrdrer();
            if (order <= 2) {
                int[] discountValuesForEach = map.getOrDefault(coupon.getTargetProductId(),
                    new int[3]);
                discountValuesForEach[order] += coupon.getDiscountValue();
                map.put(coupon.getTargetProductId(), discountValuesForEach);
            } else {
                discountValuesForAll[order] += coupon.getDiscountValue();
            }
        }

    }

    private void calculateDiscountedCost(List<CartItemResponse> responses,
            Map<Long, int[]> map, int[] discountValuesForAll) {
        for (CartItemResponse response : responses) {
            int[] discountValuesForEach = map.getOrDefault(response.getProductId(),
                new int[] {0, 0, 0});
            int fixEach = discountValuesForEach[1];
            int totalRate = discountValuesForEach[2] + discountValuesForAll[3];
            long cost = response.getAmount() * response.getPrice();
            long discountedCost = Math.max(cost - response.getAmount() * fixEach, 0);
            discountedCost -= discountedCost * totalRate / 100;
            if (cost != discountedCost) {
                response.setDiscountedCost(discountedCost);
            }
        }

    }
}
