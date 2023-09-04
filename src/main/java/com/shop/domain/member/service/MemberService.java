package com.shop.domain.member.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shop.domain.item.service.ItemService;
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

    private final ItemService itemService;

    public Member createMember(Member member) {
        return memberRepository.save(member); // Mapper에서 검증
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

    public List<CartItem> findCartItems() {
        return cartItemRepository
                .findAllByMember_MemberId(AuthUtils.getMemberId());
    }

    public CartItem findCartItemByCartItemIdAndAuthId(long cartItemId) {
        return cartItemRepository
                .findByCartItemIdAndMember_MemberId(cartItemId, AuthUtils.getMemberId())
                .orElseThrow(() -> new CustomException(ExceptionCode.CART_ITEM_NOT_FOUND));
    }

    public void deleteCartItem(long cartItemId) {
        findCartItemByCartItemIdAndAuthId(cartItemId);
        cartItemRepository.deleteById(cartItemId);
    }

    public void deleteCartItems() {
        cartItemRepository.deleteAllByMember_MemberId(AuthUtils.getMemberId());
    }
}
