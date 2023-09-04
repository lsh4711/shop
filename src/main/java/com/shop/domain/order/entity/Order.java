package com.shop.domain.order.entity;

import lombok.Getter;
import lombok.Setter;

// @Entity
@Setter
@Getter
// @NoArgsConstructor
// @AllArgsConstructor
public class Order {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Long cartId;

    // @NotNull
    // @Enumerated(EnumType.STRING)
    // private Payment payment;

    // @NotNull
    // @Enumerated(EnumType.STRING)
    // private Status status;

    // @NotNull
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "memberId")
    // private Member member;

    // @OneToMany(mappedBy = "cart", cascade = CascadeType.REMOVE)
    // private List<CartItem> cartItems;

    // public enum Payment {
    //     NONE("미결제"),
    //     POINT("포인트 결제"),
    //     CARD("신용 카드 결제"),
    //     ACCOUNT_TRANSFER("계좌 이체"),
    //     THIRD_PARTY("간편 결제");

    //     private String description;

    //     private Payment(String description) {
    //         this.description = description;
    //     }
    // }

    // public enum Status {
    //     PAYMENT_WAIT("결제 대기"),
    //     ORDER_COMPLETE("주문 완료"),
    //     // ORDER_CONFIRM("주문 확정"),
    //     ORDER_CANCEL("주문 취소"); // 주문 확정 후 취소, 환불한 경우

    //     private String description;

    //     private Status(String description) {
    //         this.description = description;
    //     }
    // }

    // @PrePersist
    // private void initPaymentAndStatus() {
    //     payment = Payment.NONE;
    //     status = Status.PAYMENT_WAIT;
    // }
}
