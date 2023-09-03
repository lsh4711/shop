package com.shop.domain.mart.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.shop.domain.mart.dto.MartDto;
import com.shop.domain.mart.dto.MartResponse;
import com.shop.domain.mart.entity.Mart;
import com.shop.global.utils.AuthUtils;

@Mapper(componentModel = "spring", imports = AuthUtils.class)
public interface MartMapper {
    @Mapping(target = "member.memberId", expression = "java(AuthUtils.getMemberId())")
    Mart postDtoToMart(MartDto.Post postDto);

    List<MartResponse> martsToMartResponse(List<Mart> marts);
}
