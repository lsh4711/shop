package com.shop.global.init;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.RestController;

import com.shop.domain.member.entity.Member;
import com.shop.domain.member.entity.Member.Role;
import com.shop.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class Init {
    private final MemberService memberService;

    @PostConstruct
    private void init() {
        for (int i = 1; i <= 5; i++) {
            Member member = new Member();
            member.setUsername("test" + i);
            member.setPassword("test" + i);
            member.setRole(Role.SELLER);
            memberService.createMember(member);
        }
    }
}
