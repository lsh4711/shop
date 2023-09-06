package com.shop.domain.brand.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;

public class BrandDto {
    @Getter
    public static class Post {
        @NotNull
        private String name;

        private String address;
    }
}
