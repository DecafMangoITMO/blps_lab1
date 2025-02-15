package com.alwxdecaf.blps_lab.order.utils;

import com.alwxdecaf.blps_lab.order.dto.OrderDto;
import com.alwxdecaf.blps_lab.order.model.Order;
import com.alwxdecaf.blps_lab.order.model.OrderStatus;
import com.alwxdecaf.blps_lab.product.model.Product;
import com.alwxdecaf.blps_lab.user.model.User;

public class OrderMapper {

    public static Order toEntity(OrderDto orderDto, User user, User customer, OrderStatus orderStatus, Integer quantity, Product product) {
        return Order.builder()
            .city(orderDto.getCity())
            .customerId(customer)
            .quantity(quantity)
            .status(orderStatus)
            .user(user)
            .street(orderDto.getStreet())
            .productId(product)
            .build();

    }

}
