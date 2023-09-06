package com.shop.domain.category.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;

public class CategoryDto {
    @Getter
    public static class Post {
        @NotNull
        private String name;
    }
}
