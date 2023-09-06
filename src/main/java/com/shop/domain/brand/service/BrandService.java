package com.shop.domain.brand.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.domain.brand.entity.Brand;
import com.shop.domain.brand.repository.BrandRespository;
import com.shop.global.exception.CustomException;
import com.shop.global.exception.ExceptionCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRespository brandRespository;

    public Brand createBrand(Brand brand) {
        return brandRespository.save(brand);
    }

    public Brand findBrand(long brandId) {
        return brandRespository.findById(brandId)
                .orElseThrow(() -> new CustomException(ExceptionCode.BRAND_NOT_FOUND));
    }

    public List<Brand> findBrands() {
        return brandRespository.findAll();
    }

    public void verifyBrandByName(String name) {
        boolean exists = brandRespository.existsByName(name);

        if (exists) {
            throw new CustomException(ExceptionCode.BRAND_EXISTS);
        }

    }
}
