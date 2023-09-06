package com.shop.domain.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByMember_MemberId(long memberId);

    Optional<Order> findByOrderIdAndMember_MemberId(long orderId, long memberId);
}
