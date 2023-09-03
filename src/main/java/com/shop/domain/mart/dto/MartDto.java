package com.shop.domain.mart.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

public class MartDto {
    @Getter
    @Setter
    public static class Post {
        @NotBlank
        @Length(max = 20, message = "마트 이름은 20자 이하여야 합니다.")
        private String name;

        @NotBlank
        @Length(max = 50, message = "마트 주소는 50자 이하여야 합니다.")
        private String address;
    }

}
