package com.shop.domain.category.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

public class CategoryDto {
    @Setter
    @Getter
    public static class Post {
        @NotNull
        private String name;
    }
}
