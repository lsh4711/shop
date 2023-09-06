package com.shop.domain.brand.mapper;

import java.util.List;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import com.shop.domain.brand.dto.BrandDto;
import com.shop.domain.brand.dto.BrandResponse;
import com.shop.domain.brand.entity.Brand;
import com.shop.domain.brand.service.BrandService;

@Mapper(componentModel = "spring")
public abstract class BrandMapper {
    @Autowired
    private BrandService brandService;

    @BeforeMapping
    void verifyPostDto(BrandDto.Post postDto, @MappingTarget Brand brand) {
        brandService.verifyBrandByName(postDto.getName());
    }

    public abstract Brand postDtoToBrand(BrandDto.Post postDto);

    public abstract List<BrandResponse> brandsToBrandResponses(List<Brand> brands);
}
