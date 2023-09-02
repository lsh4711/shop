package com.shop.domain.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shop.domain.member.entity.Member;
import com.shop.domain.member.repository.MemberRepository;
import com.shop.global.exception.CustomException;
import com.shop.global.exception.ExceptionCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public Member createMember(Member member) {
        verifyUsername(member.getUsername());
        encryptPassword(member);

        return memberRepository.save(member);
    }

    public Member findMember(long memberId) {
        Member member = memberRepository.findById(memberId).get();

        if (member == null) {
            throw new CustomException(ExceptionCode.MEMBER_NOT_FOUND);
        }

        return member;
    }

    public Member findMemberByUsername(String username) {
        Member member = memberRepository.findByUsername(username);

        if (member == null) {
            throw new CustomException(ExceptionCode.MEMBER_NOT_FOUND);
        }

        return member;
    }

    private void verifyUsername(String username) {
        boolean exists = memberRepository.existsByUsername(username);

        if (exists) {
            throw new CustomException(ExceptionCode.USERNAME_EXISTS);
        }

    }

    private Member encryptPassword(Member member) {
        String encryptedPassword = passwordEncoder.encode(member.getPassword());

        member.setPassword(encryptedPassword);

        return member;
    }
}
