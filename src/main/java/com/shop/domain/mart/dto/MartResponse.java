package com.shop.domain.mart.dto;

import lombok.Getter;
import lombok.Setter;

public class MartResponse {
    @Setter
    @Getter
    public static class Public {
        private long martId;
        private String name;
        private String address;
        private String createdAt;
    }

    @Setter
    @Getter
    public static class Private {
        private long martId;
        private String name;
        private String address;
        private String createdAt;
        private String modifiedAt;
        private Long money;
    }
}
