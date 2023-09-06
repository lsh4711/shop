package com.shop.domain.order.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import com.shop.domain.member.entity.Member;
import com.shop.global.audit.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "OrderHistory")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @NotNull
    private Long deliveryFee;

    @NotNull
    private Long totalCost;

    private Long discountedDeliveryFee;
    private Long discountedTotalCost;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Payment payment;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @NotNull
    @OneToMany(mappedBy = "order", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<OrderItem> orderitems;

    public enum Status {
        PAYMENT_WAIT(1, "결제 대기"),
        ORDER_COMPLETE(2, "주문 완료"),
        // ORDER_CONFIRM("주문 확정"),
        ORDER_CANCEL(-1, "환불");

        private int order;
        private String description;

        private Status(int order, String description) {
            this.order = order;
            this.description = description;
        }
    }

    public enum Payment {
        NONE("미결제"),
        ACCOUNT_TRANSFER("계좌 이체"),
        CARD("신용 카드 결제"),
        POINT("포인트 결제"),
        THIRD_PARTY("간편 결제");

        private String description;

        private Payment(String description) {
            this.description = description;
        }
    }

    @PrePersist
    private void initPaymentAndStatus() {
        if (status == null) {
            status = Status.PAYMENT_WAIT;
        }
        if (payment == null) {
            payment = Payment.NONE;
        }

    }
}
