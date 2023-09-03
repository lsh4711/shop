package com.shop.domain.item.service;

import org.springframework.stereotype.Service;

import com.shop.domain.item.entity.Item;
import com.shop.domain.item.repository.ItemRepository;
import com.shop.domain.mart.service.MartService;
import com.shop.domain.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    private final MartService martService;

    private final ProductService productService;

    public Item createItem(Item item) {
        verifyRequest(item);

        return itemRepository.save(item);
    }

    private void verifyRequest(Item item) {
        long martId = item.getMart().getMartId();
        long productId = item.getProduct().getProductId();

        martService.findByMartIdAndAuthId(martId); // 본인 소유의 마트인지 검증
        productService.findProduct(productId); // 존재하는 제품인지 검증
        verifyItem(martId, productId); // 중복 상품인지 검증
    }

    private void verifyItem(long martId, long productId) {
        itemRepository.existsByMart_MartIdAndProduct_ProductId(martId, productId);
    }
}
