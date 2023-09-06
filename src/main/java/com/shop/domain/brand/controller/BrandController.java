package com.shop.domain.brand.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.domain.brand.dto.BrandDto;
import com.shop.domain.brand.dto.BrandResponse;
import com.shop.domain.brand.entity.Brand;
import com.shop.domain.brand.mapper.BrandMapper;
import com.shop.domain.brand.service.BrandService;
import com.shop.global.utils.UriCreator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brands")
public class BrandController {
    private final BrandService brandService;
    private final BrandMapper brandMapper;

    @PostMapping("/register")
    public ResponseEntity postBrand(@Valid @RequestBody BrandDto.Post postDto) {
        Brand brand = brandMapper.postDtoToBrand(postDto);
        Brand savedBrand = brandService.createBrand(brand);

        URI location = UriCreator.createUri("/api/brands", savedBrand.getBrandId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity getBrands() {
        List<Brand> brands = brandService.findBrands();

        List<BrandResponse> brandResponses = brandMapper.brandsToBrandResponses(brands);

        return ResponseEntity.ok(brandResponses);
    }
}
