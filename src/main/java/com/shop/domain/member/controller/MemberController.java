package com.shop.domain.member.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.domain.coupon.dto.CouponDto;
import com.shop.domain.coupon.service.CouponService;
import com.shop.domain.member.dto.CartItemDto;
import com.shop.domain.member.dto.CartItemResponse;
import com.shop.domain.member.dto.CartResponse;
import com.shop.domain.member.dto.MemberDto;
import com.shop.domain.member.entity.CartItem;
import com.shop.domain.member.entity.Member;
import com.shop.domain.member.mapper.MemberMapper;
import com.shop.domain.member.service.MemberService;
import com.shop.global.utils.UriCreator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    private final CouponService couponService;

    @PostMapping("/register")
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post postDto) {
        Member member = memberMapper.postDtoToMember(postDto);
        Member savedMember = memberService.createMember(member);

        URI location = UriCreator
                .createUri("/members", savedMember.getMemberId());

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping
    public ResponseEntity deleteMember() {
        memberService.deleteMember();

        return ResponseEntity.noContent().build();
    }

    // CartItem

    @PostMapping("/cart/items/add")
    public ResponseEntity postCartItem(@Valid @RequestBody CartItemDto.Post postDto) {
        CartItem cartItem = memberMapper.cartItemPostDtoToCartItem(postDto);
        CartItem savedCartItem = memberService.addCartItem(cartItem);

        URI location = UriCreator.createManualUri("/members/{memberId}/cart/items/{itemId}",
            savedCartItem.getMember().getMemberId(),
            savedCartItem.getCartItemId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/cart/items/{cartItemId}/edit")
    public ResponseEntity patchCartItem(@PathVariable long cartItemId,
            @Valid @RequestBody CartItemDto.Patch patchDto) {
        CartItem cartItem = memberMapper.cartItemPatchDtoToCartItem(patchDto, cartItemId);
        CartItem updatedCartItem = memberService.updateCartItemInfo(cartItem);

        CartItemResponse cartItemResponse = memberMapper
                .cartItemToCartItemResponse(updatedCartItem);

        return ResponseEntity.ok(cartItemResponse);
    }

    @GetMapping("/cart/items")
    public ResponseEntity getCartItems() {
        List<CartItem> cartItems = memberService.findCartItems();

        List<CartItemResponse> cartItemResponses = memberMapper
                .cartItemsToCartItemResponses(cartItems);
        CartResponse cartResponse = memberMapper.cartItemResponsesToCartResponse(cartItemResponses,
            0);

        return ResponseEntity.ok(cartResponse);
    }

    @DeleteMapping("/cart/items/{cartItemId}")
    public ResponseEntity deleteCartItems(@PathVariable long cartItemId) {
        memberService.deleteCartItem(cartItemId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/cart/items")
    public ResponseEntity deleteCartItems() {
        memberService.deleteCartItems();

        return ResponseEntity.noContent().build();
    }

    // Coupon

    @PatchMapping("/cart/items/discount")
    public ResponseEntity patchCartItemCost(@Valid @RequestBody CouponDto.Patch couponPatchDto) {
        List<CartItem> cartItems = memberService.findCartItems();

        List<CartItemResponse> cartItemResponses = memberMapper
                .cartItemsToCartItemResponses(cartItems);
        int fix = memberService
                .discountCartItems(cartItemResponses, couponPatchDto.getCouponIds());
        CartResponse cartResponse = memberMapper
                .cartItemResponsesToCartResponse(cartItemResponses, fix);

        return ResponseEntity.ok(cartResponse);
    }
}
