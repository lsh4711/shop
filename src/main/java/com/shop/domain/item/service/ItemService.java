package com.shop.domain.item.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.domain.item.entity.Item;
import com.shop.domain.item.entity.PriceHistory;
import com.shop.domain.item.repository.ItemRepository;
import com.shop.domain.item.repository.PriceHistoryRepository;
import com.shop.domain.mart.entity.Mart;
import com.shop.domain.member.entity.Member;
import com.shop.global.exception.CustomException;
import com.shop.global.exception.ExceptionCode;
import com.shop.global.utils.AuthUtils;
import com.shop.global.utils.CustomBeanUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    private final PriceHistoryRepository priceHistoryRepository;

    public Item createItem(Item item) {
        Item savedItem = itemRepository.save(item);

        createPriceHistory(savedItem);

        return savedItem;
    }

    public Item updateItem(Item item) {
        Item foundItem = findItemByItemIdAndMartId(item.getItemId(), item.getMartId());

        CustomBeanUtils.copyNonNullProperties(item, foundItem);
        createPriceHistory(foundItem);

        return itemRepository.save(foundItem);
    }

    public Item findItem(long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new CustomException(ExceptionCode.ITEM_NOT_FOUND));
    }

    public List<Item> findItemsOfMart(long martId) {
        return itemRepository.findAllByMart_MartId(martId);
    }

    public Item findItemByItemIdAndMartId(long itemId, long martId) {
        return itemRepository.findByItemIdAndMart_MartId(itemId, martId)
                .orElseThrow(() -> new CustomException(ExceptionCode.ITEM_NOT_FOUND));
    }

    public void deleteItem(long itemId) {
        verifyItemOwner(itemId);
        itemRepository.deleteById(itemId);
    }

    public void verifyItemOwner(long itemId) {
        long memberId = itemRepository.findById(itemId)
                .map(Item::getMart)
                .map(Mart::getMember)
                .map(Member::getMemberId)
                .orElseThrow(() -> new CustomException(ExceptionCode.ITEM_NOT_FOUND));

        if (memberId != AuthUtils.getMemberId()) {
            throw new CustomException(ExceptionCode.FORBIDDEN);
        }

    }

    public void verifyMartIdAndProductId(long martId, long productId) {
        boolean exists = itemRepository.existsByMart_MartIdAndProduct_ProductId(martId, productId);

        if (exists) {
            throw new CustomException(ExceptionCode.ITEM_EXISTS);
        }

    }

    // PriceHistory

    public void createPriceHistory(Item item) {
        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setItem(item);
        priceHistory.setPrice(item.getPrice());

        priceHistoryRepository.save(priceHistory);
    }

    public PriceHistory findPriceHistoryByItemIdAndDate(long itemId, LocalDateTime date) {
        return priceHistoryRepository
                .findFirstByItem_ItemIdAndCreatedAtLessThanOrderByPriceHistoryIdDesc(
                    itemId, date)
                .orElseThrow(() -> new CustomException(ExceptionCode.PRICE_HISTORY_NOT_FOUND));
    }

    private List<PriceHistory> findPriceHistoriesFromStartDateToNow(
            long itemId, LocalDateTime startDate) {
        startDate = priceHistoryRepository
                .findFirstByItem_ItemIdAndCreatedAtLessThanOrderByPriceHistoryIdDesc(
                    itemId, startDate.plusDays(1))
                .map(PriceHistory::getCreatedAt)
                .orElse(startDate);
        List<PriceHistory> priceHistories = priceHistoryRepository
                .findAllByItem_ItemIdAndCreatedAtGreaterThanEqualOrderByPriceHistoryId(
                    itemId, startDate);

        if (priceHistories.size() == 0) {
            throw new CustomException(ExceptionCode.PRICE_HISTORY_NOT_FOUND);
        }

        return priceHistories;
    }

    public PriceHistory[] generateRecentPriceHistoryChart(long itemId, int days,
            LocalDate startDate) {
        int idx = 0;
        PriceHistory[] priceHistories = new PriceHistory[days];
        List<PriceHistory> foundPriceHistories = findPriceHistoriesFromStartDateToNow(
            itemId, startDate.atStartOfDay());

        for (PriceHistory priceHistory : foundPriceHistories) {
            int nextIdx = (int)ChronoUnit.DAYS.between(startDate, priceHistory.getCreatedAt());
            while (idx < nextIdx) {
                priceHistories[++idx] = priceHistories[idx - 1];
            }
            priceHistories[idx] = priceHistory;
        }
        while (++idx < days) {
            priceHistories[idx] = priceHistories[idx - 1];
        }

        return priceHistories;
    }
}
