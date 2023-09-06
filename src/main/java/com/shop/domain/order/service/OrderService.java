package com.shop.domain.order.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.domain.item.entity.Item;
import com.shop.domain.mart.service.MartService;
import com.shop.domain.member.entity.CartItem;
import com.shop.domain.order.entity.Order;
import com.shop.domain.order.repository.OrderRepository;
import com.shop.global.exception.CustomException;
import com.shop.global.exception.ExceptionCode;
import com.shop.global.utils.AuthUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private final MartService martService;

    public Order createOrder(Order order) {
        if (order.getOrderitems().size() == 0) {
            throw new CustomException(ExceptionCode.CART_EMPTY);
        }

        return orderRepository.save(order);
    }

    public List<Order> findOrdersOfAuthMember() {
        return orderRepository.findAllByMember_MemberId(AuthUtils.getMemberId());
    }

    public Order findOrder(long orderId) {
        return orderRepository.findByOrderIdAndMember_MemberId(orderId, AuthUtils.getMemberId())
                .orElseThrow(() -> new CustomException(ExceptionCode.ORDER_NOT_FOUND));
    }

    public void addMoneyToMart(List<CartItem> cartItems) {
        for (CartItem cartItem : cartItems) {
            Item item = cartItem.getItem();
            martService.addMoney(
                item.getMart(), cartItem.getAmount() * item.getPrice());
        }

    }
}
