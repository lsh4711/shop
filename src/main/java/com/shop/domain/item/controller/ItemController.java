package com.shop.domain.item.controller;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import com.shop.domain.item.dto.PriceHistoryResponse;
import com.shop.domain.item.entity.Item;
import com.shop.domain.item.entity.PriceHistory;
import com.shop.domain.item.mapper.ItemMapper;
import com.shop.domain.item.service.ItemService;
import com.shop.domain.product.dto.ProductResponse;
import com.shop.domain.product.mapper.ProductMapper;
import com.shop.global.utils.UriCreator;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    private final ProductMapper productMapper;

    @Transactional
    @PostMapping("/register")
    public ResponseEntity postItem(@Valid @RequestBody ItemDto.Post postDto) {
        Item item = itemMapper.postDtoToItem(postDto);
        Item savedItem = itemService.createItem(item);

        URI location = UriCreator.createUri("/api/items", savedItem.getItemId());

        return ResponseEntity.created(location).build();
    }

    @Transactional
    @PatchMapping("/{itemId}/edit")
    public ResponseEntity patchItem(@PathVariable long itemId,
            @Valid @RequestBody ItemDto.Patch patchDto) {
        Item item = itemMapper.patchDtoToItem(patchDto, itemId);
        Item updatedItem = itemService.updateItem(item);

        ProductResponse productResponse = productMapper
                .productToProductResponse(updatedItem.getProduct());
        ItemResponse itemResponse = itemMapper.itemToItemResponse(updatedItem, productResponse);

        return ResponseEntity.ok(itemResponse);
    }

    @GetMapping
    public ResponseEntity getItems(@RequestParam long martId) {
        List<Item> items = itemService.findItemsOfMart(martId);

        List<ItemResponse> itemResponses = items.stream().map(item -> {
            ProductResponse productResponse = productMapper
                    .productToProductResponse(item.getProduct());
            return itemMapper.itemToItemResponse(item, productResponse);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(itemResponses);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity getItem(@PathVariable long itemId) {
        Item item = itemService.findItem(itemId);

        ProductResponse productResponse = productMapper
                .productToProductResponse(item.getProduct());
        ItemResponse itemResponse = itemMapper.itemToItemResponse(item, productResponse);

        return ResponseEntity.ok(itemResponse);
    }

    // 하위 테이블에 데이터가 있는 경우, 삭제 대신 노출 상태를 변경하는 방법도 고려
    @DeleteMapping("/{itemId}")
    public ResponseEntity deleteItem(@PathVariable long itemId) {
        itemService.deleteItem(itemId);

        return ResponseEntity.noContent().build();
    }

    // PriceHistory

    @GetMapping("/{itemId}/price/histories/search")
    public ResponseEntity getPriceHistory(@PathVariable long itemId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        PriceHistory priceHistory = itemService.findPriceHistoryByItemIdAndDate(itemId, date);

        PriceHistoryResponse priceHistoryResponse = itemMapper
                .priceHistoryToPriceHistoryResponse(priceHistory);
        priceHistoryResponse.setDate(date);

        return ResponseEntity.ok(priceHistoryResponse);
    }

    @GetMapping("/{itemId}/price/histories")
    public ResponseEntity getPriceHistories(@PathVariable long itemId,
            @RequestParam @Min(1) @Max(90) int days) {
        LocalDate startDate = LocalDate.now().minusDays(days - 1);
        PriceHistory[] priceHistoryChart = itemService.generateRecentPriceHistoryChart(itemId, days,
            startDate);

        List<PriceHistoryResponse> priceHistoryResponses = itemMapper
                .priceHistoryChartToPriceHistoryResponses(priceHistoryChart);
        PriceHistoryResponse.setAllDateInOrder(priceHistoryResponses, startDate);

        return ResponseEntity.ok(priceHistoryResponses);
    }
}
