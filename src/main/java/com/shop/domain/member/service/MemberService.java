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
        return memberRepository.save(member); // Mapper에서 검증
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

    public void verifyUsername(String username) {
        boolean exists = memberRepository.existsByUsername(username);

        if (exists) {
            throw new CustomException(ExceptionCode.MEMBER_EXISTS);
        }

    }

    public String encryptPassword(String password) {
        String encryptedPassword = passwordEncoder.encode(password);

        return encryptedPassword;
    }
}
