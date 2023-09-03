package com.shop.domain.member.mapper;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import com.shop.domain.member.dto.MemberDto;
import com.shop.domain.member.entity.Member;
import com.shop.domain.member.service.MemberService;

@Mapper(componentModel = "spring")
public abstract class MemberMapper {
    @Autowired
    MemberService memberService;

    @BeforeMapping
    void verifyPostDto(MemberDto.Post postDto, @MappingTarget Member member) {
        memberService.verifyUsername(postDto.getUsername());
    }

    @Mapping(target = "password",
            expression = "java(memberService.encryptPassword(postDto.getPassword()))")
    public abstract Member postDtoToMember(MemberDto.Post postDto);
}
