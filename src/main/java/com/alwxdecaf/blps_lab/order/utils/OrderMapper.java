package com.alwxdecaf.blps_lab.order.utils;

import java.util.List;

import com.alwxdecaf.blps_lab.order.dto.OrderDto;
import com.alwxdecaf.blps_lab.order.model.Order;
import com.alwxdecaf.blps_lab.order.model.OrderStatus;
import com.alwxdecaf.blps_lab.product.model.Product;
import com.alwxdecaf.blps_lab.user.model.User;

public class OrderMapper {

    public static Order toEntity(OrderDto orderDto, User user, List<Product> products, OrderStatus orderStatus) {
        return Order.builder()
            .city(orderDto.getCity())
            .products(products)
            .status(orderStatus)
            .user(user)
            .street(orderDto.getStreet())
            .build();

    }

    public static OrderDto toDto(Order order) {
        return OrderDto.builder()
                .city(order.getCity())
                .street(order.getStreet())
                .products(order.getProducts().stream().map(Product::getId).toList())
                .build();
    }

}
