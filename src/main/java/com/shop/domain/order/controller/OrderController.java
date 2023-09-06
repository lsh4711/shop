package com.shop.domain.order.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.domain.coupon.service.CouponService;
import com.shop.domain.member.dto.CartItemResponse;
import com.shop.domain.member.dto.CartResponse;
import com.shop.domain.member.entity.CartItem;
import com.shop.domain.member.mapper.MemberMapper;
import com.shop.domain.member.service.MemberService;
import com.shop.domain.order.dto.OrderDto;
import com.shop.domain.order.dto.OrderListResponse;
import com.shop.domain.order.dto.OrderResponse;
import com.shop.domain.order.entity.Order;
import com.shop.domain.order.mapper.OrderMapper;
import com.shop.domain.order.service.OrderService;
import com.shop.global.utils.UriCreator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    private final MemberService memberService;
    private final MemberMapper memberMapper;

    private final CouponService couponService;

    @Transactional
    @PostMapping("/pay")
    public ResponseEntity postOrder(@Valid @RequestBody OrderDto.Post postDto) {
        List<CartItem> cartItems = memberService.findCartItems();
        List<CartItemResponse> cartItemResponses = memberMapper
                .cartItemsToCartItemResponses(cartItems);
        int fix = memberService
                .discountCartItems(cartItemResponses, postDto.getCouponIds());
        CartResponse cartResponse = memberMapper
                .cartItemResponsesToCartResponse(cartItemResponses, fix);

        Order order = orderMapper.getSaveOrderFromCartResponse(cartResponse, postDto.getPayment());
        Order savedOrder = orderService.createOrder(order);

        couponService.deleteCouponsByCouponIdsAndAuthId(postDto.getCouponIds());
        orderService.addMoneyToMart(cartItems);
        memberService.deleteCartItems();

        URI location = UriCreator.createUri("/api/orders", savedOrder.getOrderId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity getOrders() {
        List<Order> orders = orderService.findOrdersOfAuthMember();

        List<OrderListResponse> orderListResponses = orderMapper.ordersToOrderListResponses(orders);

        return ResponseEntity.ok(orderListResponses);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity getOrder(@PathVariable long orderId) {
        Order order = orderService.findOrder(orderId);

        OrderResponse orderResponse = orderMapper.orderToOrderResponse(order);

        return ResponseEntity.ok(orderResponse);
    }
}
