package com.shop.domain.member.mapper;

import org.mapstruct.Mapper;

import com.shop.domain.member.dto.MemberDto;
import com.shop.domain.member.entity.Member;

@Mapper(componentModel = "spring")
public abstract class MemberMapper {

    public abstract Member postDtoToMember(MemberDto.Post postDto);
}
