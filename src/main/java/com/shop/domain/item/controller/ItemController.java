package com.shop.domain.item.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.domain.item.dto.ItemDto;
import com.shop.domain.item.dto.ItemResponse;
import com.shop.domain.item.entity.Item;
import com.shop.domain.item.mapper.ItemMapper;
import com.shop.domain.item.service.ItemService;
import com.shop.domain.product.dto.ProductResponse;
import com.shop.domain.product.mapper.ProductMapper;
import com.shop.global.utils.UriCreator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    private final ProductMapper productMapper;

    @PostMapping("/write")
    public ResponseEntity postItem(@Valid @RequestBody ItemDto.Post postDto) {
        Item item = itemMapper.postDtoToItem(postDto);
        Item savedItem = itemService.createItem(item);

        URI location = UriCreator.createUri("/api/items", savedItem.getItemId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/edit")
    public ResponseEntity patchItem(@Valid @RequestBody ItemDto.Patch patchDto) {
        Item item = itemMapper.patchDtoToItem(patchDto);
        Item updatedItem = itemService.updateItem(item);

        ProductResponse productResponse = productMapper
                .productToProductResponse(updatedItem.getProduct());
        ItemResponse itemResponse = itemMapper.itemToItemResponse(updatedItem, productResponse);

        return ResponseEntity.ok(itemResponse);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity getItem(@PathVariable long itemId) {
        Item item = itemService.findItem(itemId);

        ProductResponse productResponse = productMapper
                .productToProductResponse(item.getProduct());
        ItemResponse itemResponse = itemMapper.itemToItemResponse(item, productResponse);

        return ResponseEntity.ok(itemResponse);
    }

    @GetMapping
    public ResponseEntity getItems(@RequestParam long martId) {
        List<Item> items = itemService.findItemsOfMart(martId);

        List<ItemResponse> itemResponses = items.stream().map(item -> {
            ProductResponse productResponse = productMapper
                    .productToProductResponse(item.getProduct());
            return itemMapper.itemToItemResponse(item, productResponse);
        }).toList();

        return ResponseEntity.ok(itemResponses);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity deleteItem(@PathVariable long itemId) {
        itemService.deleteItem(itemId);

        return ResponseEntity.noContent().build();
    }
}
