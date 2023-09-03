package com.shop.domain.item.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.domain.item.dto.ItemDto;
import com.shop.domain.item.entity.Item;
import com.shop.domain.item.mapper.ItemMapper;
import com.shop.domain.item.service.ItemService;
import com.shop.global.utils.UriCreator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @PostMapping("/write")
    public ResponseEntity postItem(@Valid @RequestBody ItemDto.Post postDto) {
        Item item = itemMapper.postDtoToItem(postDto);
        Item savedItem = itemService.createItem(item);

        URI location = UriCreator.createUri("/api/items", savedItem.getItemId());

        return ResponseEntity.created(location).build();
    }
}
