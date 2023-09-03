package com.shop.domain.item.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.shop.domain.item.dto.ItemDto;
import com.shop.domain.item.entity.Item;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    @Mapping(target = "mart.martId", source = "martId")
    @Mapping(target = "product.productId", source = "productId")
    Item postDtoToItem(ItemDto.Post postDto);
}
