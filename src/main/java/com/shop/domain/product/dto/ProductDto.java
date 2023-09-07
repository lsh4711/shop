package com.shop.domain.product.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

public class ProductDto {
    @Setter
    @Getter
    public static class Post {
        @NotBlank
        @Length(max = 20, message = "상품 이름은 20자 이하여야 합니다.")
        private String name;

        @NotBlank
        @Pattern(regexp = "^\\S{1,16}$", message = "상품 코드는 공백을 제외한 16자 이하의 문자열이어야 합니다.")
        private String barcode;

        @NotNull
        private Long brandId;

        @NotNull
        private List<Long> categoryIds;
    }
}
