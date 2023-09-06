package com.shop.domain.coupon.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.shop.domain.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponId;

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Info info;

    @NotNull
    private Integer discountValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    private Long targetProductId;

    @Getter
    public enum Info {
        FIX_EACH(1, "원", "each", "특정 상품 고정 할인"),
        RATE_EACH(2, "%", "each", "특정 상품 비율 할인"),
        RATE_ALL(3, "%", "all", "전체 상품 비율 할인"),
        FIX_ALL(4, "원", "all", "전체 상품 고정 할인");

        private int ordrer;
        private String unit;
        private String range;
        private String description;

        private Info(int order, String unit, String range, String description) {
            this.ordrer = order;
            this.unit = unit;
            this.range = range;
            this.description = description;
        }
    }
}
