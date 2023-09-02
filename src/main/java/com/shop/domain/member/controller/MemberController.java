package com.shop.domain.member.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.domain.member.dto.MemberDto;
import com.shop.domain.member.entity.Member;
import com.shop.domain.member.mapper.MemberMapper;
import com.shop.domain.member.service.MemberService;
import com.shop.global.utils.UriCreator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @PostMapping("/write")
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post postDto) {
        Member member = memberMapper.postDtoToMember(postDto);
        Member savedMember = memberService.createMember(member);

        URI location = UriCreator
                .createUri("/api/members", savedMember.getMemberId());

        return ResponseEntity.created(location).build();
    }
}
