package com.shop.domain.item.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.domain.item.entity.Item;
import com.shop.domain.item.repository.ItemRepository;
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

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Item item) {
        Item foundItem = findItemByItemIdAndMartId(item.getItemId(), item.getMartId());
        CustomBeanUtils.copyNonNullProperties(item, foundItem);

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
}
