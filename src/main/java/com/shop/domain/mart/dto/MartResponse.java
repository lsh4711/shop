package com.shop.domain.mart.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MartResponse {
    private long martId;
    private String name;
    private String address;
    private String createdAt;
    // private String modifiedAt;
}
