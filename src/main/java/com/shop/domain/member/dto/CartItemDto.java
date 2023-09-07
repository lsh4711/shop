package com.shop.domain.member.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

public class CartItemDto {
    @Setter
    @Getter
    public static class Post {
        @Min(value = 1, message = "하나 이상의 수량을 추가해야 합니다.")
        private long amount;

        @NotNull
        private Long itemId;
    }

    @Setter
    @Getter
    public static class Patch {
        @Min(value = 1, message = "하나 이상의 수량으로 변경해주세요.")
        private long amount;
    }
}
