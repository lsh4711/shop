package com.shop.domain.product.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.domain.product.dto.ProductDto;
import com.shop.domain.product.entity.Product;
import com.shop.domain.product.mapper.ProductMapper;
import com.shop.domain.product.service.ProductService;
import com.shop.global.utils.UriCreator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity postProduct(@Valid @RequestBody ProductDto.Post postDto) {
        Product product = productMapper.postDtoToProduct(postDto);
        Product savedProduct = productService.createProduct(product);

        URI location = UriCreator.createUri("/products", savedProduct.getProductId());

        return ResponseEntity.created(location).build();
    }
}
