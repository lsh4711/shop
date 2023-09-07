package com.shop.global.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.shop.domain.member.entity.Member;
import com.shop.domain.member.service.MemberService;
import com.shop.global.auth.filter.JwtAuthenticationToken;
import com.shop.global.exception.CustomException;
import com.shop.global.exception.ExceptionCode;

public class AuthUtils {
    public static long getMemberId() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        if (authentication == null) {
            throw new CustomException(ExceptionCode.UNAUTHORIZED);
        }

        long memberId = ((JwtAuthenticationToken)authentication).getCredentials();

        return memberId;
    }

    public static Member getMember(MemberService memberService) {
        long memberId = getMemberId();
        Member member = memberService.findMember(memberId);

        return member;
    }

    public static String getTestToken() {
        return "Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlcyI6WyJST0xFX1NFTExFUiIsIlJPTEVfVVNFUiJdLCJpZCI6MSwidXNlcm5hbWUiOiJtZW1iZXIxIiwiaWF0IjoxNjk0MDQ0MTQ5LCJleHAiOjE2OTY2MzYxNDl9.Q42NORZvS68EhPzp4pGkQ1u8pmTYzGlc-pMi6FhPU1AT1sANJHjFohvSQcK2wB6XRPFr29xJclh2tXEzznWaXA";
    }

    public static String getDeleteToken() {
        return "Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWQiOjExLCJ1c2VybmFtZSI6Im1lbWJlcjExIiwiaWF0IjoxNjk0MDYzODg1LCJleHAiOjE2OTY2NTU4ODV9.ohLhCg_8ATHQg8ywMyHLPSIvrtkKyXctJ5k9hWkqq_7L-N7HEXr22SzSS-Yw-BXOcBBqr_NjR9zifsYSMBjdmA";
    }
}
