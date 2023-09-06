package com.shop.global.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    TEST_CODE("테스트", 200),

    // 인증
    SIGNATURE_INVALID("토큰 서명 오류", 401),
    TOKEN_EXPIRED("토큰 만료", 401),
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
    BRAND_EXISTS("중복되는 브랜드 이름입니다.", 409),
    BRAND_NOT_FOUND("브랜드를 찾을 수 없습니다.", 404),

    // 카테고리
    CATEGORY_EXISTS("중복되는 카테고리 이름입니다.", 409),
    CATEGORY_NOT_FOUND("카테고리를 찾을 수 없습니다.", 404),

    // 제품
    PRODUCT_EXISTS("중복되는 바코드입니다.", 409),
    PRODUCT_NOT_FOUND("제품을 찾을 수 없습니다.", 404),

    // 상품
    ITEM_NOT_FOUND("상품을 찾을 수 없습니다.", 404),
    ITEM_EXISTS("이미 등록된 상품입니다.", 409),

    // 상품 가격 내역
    PRICE_HISTORY_NOT_FOUND("상품이 등록되기 전 입니다.", 404),

    // 쿠폰
    COUPON_NOT_FOUND("쿠폰을 찾을 수 없습니다.", 404),

    // 주문
    CART_EMPTY("장바구니가 비어있습니다.", 404),
    ORDER_NOT_FOUND("주문을 찾을 수 없습니다.", 404),

    END("end", 200);

    private final String message;
    private final int statusCode;

    ExceptionCode(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
