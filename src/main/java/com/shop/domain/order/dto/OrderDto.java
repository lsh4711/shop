package com.shop.domain.order.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.shop.domain.order.entity.Order.Payment;

import lombok.Getter;
import lombok.Setter;

public class OrderDto {
    @Setter
    @Getter
    public static class Post {
        @NotNull
        private Payment payment;

        @NotNull
        @Size(max = 5, message = "쿠폰은 주문 당 최대 5개까지 사용 가능합니다.")
        private List<Long> couponIds;
    }
}
