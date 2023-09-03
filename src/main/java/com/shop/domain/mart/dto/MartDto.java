package com.shop.domain.mart.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;

public class MartDto {
    @Getter
    public static class Post {
        @NotBlank
        @Length(max = 20, message = "길이가 20자 이하여야 합니다.")
        private String name;

        @NotBlank
        @Length(max = 50, message = "길이가 50자 이하여야 합니다.")
        private String address;
    }

}
