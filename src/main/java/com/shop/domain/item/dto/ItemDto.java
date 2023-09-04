package com.shop.domain.item.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;

public class ItemDto {
    @Getter
    public static class Post {
        @Min(value = 100, message = "상품 가격은 100원 이상이어야 합니다.")
        private long price;

        @NotNull
        private Long martId;

        @NotNull
        private Long productId;
    }

    @Getter
    public static class Patch {
        @NotNull
        private Long martId;

        @Min(value = 100, message = "상품 가격은 100원 이상이어야 합니다.")
        private long price;
    }
}
