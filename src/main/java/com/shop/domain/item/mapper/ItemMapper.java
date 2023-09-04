package com.shop.domain.item.mapper;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.shop.domain.item.dto.ItemDto;
import com.shop.domain.item.dto.ItemResponse;
import com.shop.domain.item.entity.Item;
import com.shop.domain.item.service.ItemService;
import com.shop.domain.mart.service.MartService;
import com.shop.domain.product.dto.ProductResponse;
import com.shop.domain.product.service.ProductService;

@Mapper(componentModel = "spring")
public abstract class ItemMapper {
    @Autowired
    private ItemService itemService;

    @Autowired
    private MartService martService;

    @Autowired
    private ProductService productService;

    @BeforeMapping
    void verifyPostDto(ItemDto.Post postDto) {
        martService.findByMartIdAndAuthId(postDto.getMartId());
        productService.findProduct(postDto.getProductId());
        itemService.verifyMartIdAndProductId(postDto.getMartId(), postDto.getProductId());
    }

    @Mapping(target = "mart.martId", source = "martId")
    @Mapping(target = "product.productId", source = "productId")
    public abstract Item postDtoToItem(ItemDto.Post postDto);

    @BeforeMapping
    void verifyPatchDto(ItemDto.Patch patchDto) {
        martService.findByMartIdAndAuthId(patchDto.getMartId());
    }

    public abstract Item patchDtoToItem(ItemDto.Patch patchDto, long itemId);

    @Mapping(target = "martId", source = "item.mart.martId")
    @Mapping(target = "martName", source = "item.mart.name")
    public abstract ItemResponse itemToItemResponse(Item item, ProductResponse productResponse);
}
