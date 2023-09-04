package com.shop.domain.member.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.member.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartItemIdAndMember_MemberId(long cartItemId, long memberId);

    Optional<CartItem> findByItem_ItemIdAndMember_MemberId(long itemId, long memberId);

    List<CartItem> findAllByMember_MemberId(long memberId);

    @Transactional
    void deleteAllByMember_MemberId(long memberId);
}
