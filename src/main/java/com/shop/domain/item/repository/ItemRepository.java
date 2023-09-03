package com.shop.domain.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.item.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsByMart_MartIdAndProduct_ProductId(long martId, long productId);
}
