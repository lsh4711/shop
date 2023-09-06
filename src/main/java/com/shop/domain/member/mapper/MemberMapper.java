package com.shop.domain.member.mapper;

import java.util.List;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import com.shop.domain.item.service.ItemService;
import com.shop.domain.member.dto.CartItemDto;
import com.shop.domain.member.dto.CartItemResponse;
import com.shop.domain.member.dto.CartResponse;
import com.shop.domain.member.dto.MemberDto;
import com.shop.domain.member.entity.CartItem;
import com.shop.domain.member.entity.Member;
import com.shop.domain.member.service.MemberService;
import com.shop.domain.product.entity.Product;
import com.shop.global.utils.AuthUtils;

@Mapper(componentModel = "spring", imports = AuthUtils.class)
public abstract class MemberMapper {
    @Autowired
    MemberService memberService;

    @Autowired
    ItemService itemService;

    @BeforeMapping
    void verifyPostDto(MemberDto.Post postDto, @MappingTarget Member member) {
        memberService.verifyUsername(postDto.getUsername());
    }

    @Mapping(target = "password",
            expression = "java(memberService.encryptPassword(postDto.getPassword()))")
    public abstract Member postDtoToMember(MemberDto.Post postDto);

    // CartItem

    @BeforeMapping
    void verifyCartItemPostDto(CartItemDto.Post postDto, @MappingTarget CartItem cartItem) {
        itemService.findItem(postDto.getItemId());
    }

    @Mapping(target = "member.memberId", expression = "java(AuthUtils.getMemberId())")
    @Mapping(target = "item.itemId", source = "itemId")
    public abstract CartItem cartItemPostDtoToCartItem(CartItemDto.Post postDto);

    public abstract CartItem cartItemPatchDtoToCartItem(CartItemDto.Patch patchDto,
            long cartItemId);

    @Mapping(target = "itemId", source = "item.itemId")
    @Mapping(target = "price", source = "item.price")
    @Mapping(target = "productId", source = "cartItem.item.product.productId")
    @Mapping(target = "productName",
            expression = "java(getProductName(cartItem.getItem().getProduct()))")
    @Mapping(target = "cost",
            expression = "java(calculateTotalPrice(cartItem))")
    public abstract CartItemResponse cartItemToCartItemResponse(CartItem cartItem);

    public abstract List<CartItemResponse> cartItemsToCartItemResponses(List<CartItem> cartItem);

    String getProductName(Product product) {
        return String.format("%s) %s", product.getBrand().getName(), product.getName());
    }

    Long calculateTotalPrice(CartItem cartItem) {
        return cartItem.getAmount() * cartItem.getItem().getPrice();
    }

    public CartResponse cartItemResponsesToCartResponse(
            List<CartItemResponse> responses, int fix) {
        CartResponse cartResponse = new CartResponse();
        cartResponse.setCartItems(responses);

        long totalCost = 0;

        for (CartItemResponse response : responses) {
            totalCost += response.getDiscountedCost() == null
                ? response.getCost() : response.getDiscountedCost();
        }
        totalCost = totalCost > fix ? totalCost - fix : 0;
        long deliveryFee = totalCost < 50000 ? 3000 : 0;

        cartResponse.setTotalCost(totalCost + deliveryFee);
        cartResponse.setDeliveryFee(deliveryFee);

        return cartResponse;
    }
}
