package com.shop.domain.item.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.item.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsByMart_MartIdAndProduct_ProductId(long martId, long productId);

    Optional<Item> findByItemIdAndMart_MartId(long itemId, long martId);

    List<Item> findAllByMart_MartId(long martId);
}
