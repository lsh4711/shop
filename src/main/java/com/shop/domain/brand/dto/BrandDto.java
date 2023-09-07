package com.shop.domain.brand.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

public class BrandDto {
    @Setter
    @Getter
    public static class Post {
        @NotNull
        private String name;

        private String address;
    }
}
