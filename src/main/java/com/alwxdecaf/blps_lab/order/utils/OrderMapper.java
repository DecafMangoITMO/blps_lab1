package com.alwxdecaf.blps_lab.order.utils;

import com.alwxdecaf.blps_lab.order.dto.CreateOrderDto;
import com.alwxdecaf.blps_lab.order.dto.OrderDto;
import com.alwxdecaf.blps_lab.order.model.Order;
import com.alwxdecaf.blps_lab.order.model.OrderStatus;
import com.alwxdecaf.blps_lab.product.model.Product;
import com.alwxdecaf.blps_lab.user.model.User;

public class OrderMapper {

    public static Order toEntity(CreateOrderDto createOrderDto, User user, User customer, OrderStatus orderStatus, Integer quantity, Product product) {
        return Order.builder()
            .city(createOrderDto.getCity())
            .customer(customer)
            .quantity(quantity)
            .status(orderStatus)
            .user(user)
            .street(createOrderDto.getStreet())
            .product(product)
            .build();
    }

    public static OrderDto toDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .city(order.getCity())
                .status(order.getStatus())
                .quantity(order.getQuantity())
                .customerId(order.getCustomer().getId())
                .userId(order.getUser().getId())
                .productId(order.getProduct().getId())
                .build();

    }

}
