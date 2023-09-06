package com.shop.domain.order.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.shop.domain.member.dto.CartItemResponse;
import com.shop.domain.member.dto.CartResponse;
import com.shop.domain.order.dto.OrderItemResponse;
import com.shop.domain.order.dto.OrderListResponse;
import com.shop.domain.order.dto.OrderResponse;
import com.shop.domain.order.entity.Order;
import com.shop.domain.order.entity.Order.Payment;
import com.shop.domain.order.entity.Order.Status;
import com.shop.domain.order.entity.OrderItem;
import com.shop.global.utils.AuthUtils;

@Mapper(componentModel = "spring", imports = AuthUtils.class)
public abstract class OrderMapper {

    public Order getSaveOrderFromCartResponse(CartResponse cartResponse, Payment payment) {
        List<OrderItem> orderItems = cartItemResponsesToOrderItems(cartResponse.getCartItems());

        Order order = cartResponseToOrder(cartResponse);
        order.setOrderitems(orderItems);
        order.setPayment(payment);
        order.setStatus(Status.ORDER_COMPLETE);

        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(order);
        }

        return order;
    }

    @Mapping(target = "member.memberId", expression = "java(AuthUtils.getMemberId())")
    public abstract Order cartResponseToOrder(CartResponse cartResponse);

    public abstract List<OrderItem> cartItemResponsesToOrderItems(
            List<CartItemResponse> cartItemResponses);

    public abstract List<OrderListResponse> ordersToOrderListResponses(List<Order> order);

    public abstract OrderItemResponse orderItemToOrderItemResponse(OrderItem orderItem);

    public abstract OrderResponse orderToOrderResponse(Order order);
}
