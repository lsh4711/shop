package com.shop.domain.item.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.item.entity.PriceHistory;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long> {
    Optional<PriceHistory> findFirstByItem_ItemIdAndCreatedAtLessThanEqualOrderByPriceHistoryIdDesc(
            long itemId,
            LocalDateTime date);

    List<PriceHistory> findAllByItem_ItemIdAndCreatedAtGreaterThanEqualOrderByPriceHistoryId(
            long itemId,
            LocalDateTime date);
}
