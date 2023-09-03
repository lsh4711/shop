package com.shop.domain.item.service;

import org.springframework.stereotype.Service;

import com.shop.domain.item.entity.Item;
import com.shop.domain.item.repository.ItemRepository;
import com.shop.global.exception.CustomException;
import com.shop.global.exception.ExceptionCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public void verifyMartIdAndProductId(long martId, long productId) {
        boolean exists = itemRepository.existsByMart_MartIdAndProduct_ProductId(martId, productId);

        if (exists) {
            throw new CustomException(ExceptionCode.ITEM_EXISTS);
        }

    }
}
