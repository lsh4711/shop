package com.shop.domain.brand.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BrandResponse {
    private Long brandId;
    private String name;
    private String address;
}
