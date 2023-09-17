package com.shop.domain.mart.mapper;

import java.util.List;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import com.shop.domain.mart.dto.MartDto;
import com.shop.domain.mart.dto.MartResponse;
import com.shop.domain.mart.entity.Mart;
import com.shop.domain.mart.service.MartService;
import com.shop.global.utils.AuthUtils;

@Mapper(componentModel = "spring", imports = AuthUtils.class)
public abstract class MartMapper {
    @Autowired
    private MartService martService;

    @BeforeMapping
    void verifyPostDto(MartDto.Post postDto, @MappingTarget Mart mart) {
        martService.verifyMartCount();
        martService.verifyName(postDto.getName());
    }

    @Mapping(target = "member.memberId", expression = "java(AuthUtils.getMemberId())")
    public abstract Mart postDtoToMart(MartDto.Post postDto);

    public abstract MartResponse.Public martToPublicMartResponse(Mart mart);

    public abstract List<MartResponse.Public> martsToPublicMartResponses(List<Mart> marts);

    public abstract List<MartResponse.Private> martsToPrivateMartResponses(List<Mart> marts);
}
