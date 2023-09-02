package com.shop.global.utils;

import java.net.URI;

import org.springframework.web.util.UriComponentsBuilder;

public class UriCreator {
    public static URI createUri(String baseUrl, long id) {
        return UriComponentsBuilder
            .newInstance()
            .path(baseUrl + "/{id}")
            .buildAndExpand(id)
            .toUri();
    }
}
