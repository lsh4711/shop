package com.shop.global.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    TEST_CODE("테스트", 200),
    LOG_NOT_FOUND("로그 파일이 존재하지 않습니다.", 404),
    INTERNAL_SERVER_ERROR("Internal Server Error", 500),
    NOT_IMPLEMENTATION("Not Implementation", 501),

    // 인증
    SIGNATURE_INVALID("토큰 서명 오류", 401),
    TOKEN_EXPIRED("토큰 만료", 401),
    TOKEN_FORMAT_INVALID("올바르지 않는 토큰입니다.", 401),
    AUTH_MAIL_CODE_MISMATCH("인증코드가 올바르지 않습니다.", 403),
    AUTH_MAIL_CODE_NOT_FOUND("발급받은 인증코드를 찾을 수 없습니다. 인증코드를 다시 받아주세요.", 404),
    REFRESH_TOKEN_NOT_FOUND("토큰을 찾을 수 없거나 만료됐습니다. 다시 로그인해주세요.", 401),
    ACCESS_TOKEN_NOT_FOUND("엑세스 토큰을 찾을 수 없습니다.", 401),
    MAIL_SEND_FAIL("Send mail fail", 500),
    NON_REGISTERED_USER("회원가입하지 않은 이메일입니다.", 400),
    PASSWORD_INVALID("비밀번호가 맞지 않습니다.", 400),
    UNAUTHORIZED("인증되지 않았습니다.", 401),
    FORBIDDEN("권한이 없습니다.", 403),

    // 회원
    MEMBER_NOT_FOUND("회원을 찾을 수 없습니다.", 404),
    MEMBER_EXISTS("중복되는 아이디입니다.", 409),

    // 장바구니
    CART_ITEM_NOT_FOUND("장바구니에 없는 상품입니다.", 404),

    // 마트
    MART_NOT_FOUND("본인 소유의 마트를 찾을 수 없습니다.", 404),
    MART_EXISTS("중복되는 마트 이름입니다.", 409),
    MART_COUNT_MAXIMUM("보유 마트 수는 5개를 넘길 수 없습니다.", 409),

    // 브랜드
    BRAND_NOT_FOUND("브랜드를 찾을 수 없습니다.", 404),

    // 카테고리
    CATEGORY_NOT_FOUND("카테고리를 찾을 수 없습니다.", 404),

    // 제품
    PRODUCT_NOT_FOUND("제품을 찾을 수 없습니다.", 404),

    // 상품
    ITEM_NOT_FOUND("상품을 찾을 수 없습니다.", 404),
    ITEM_EXISTS("이미 등록된 상품입니다.", 409),

    END("end", 200);

    private final String message;
    private final int statusCode;

    ExceptionCode(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
