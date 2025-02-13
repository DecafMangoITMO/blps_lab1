package com.alwxdecaf.blps_lab.product.util;

import com.alwxdecaf.blps_lab.product.dto.ProductDto;
import com.alwxdecaf.blps_lab.product.dto.PublishProductDto;
import com.alwxdecaf.blps_lab.product.model.Product;
import com.alwxdecaf.blps_lab.user.model.User;

public class ProductMapper {

    public static Product toEntity(PublishProductDto publishProductDto, User user) {
        return Product.builder()
                .name(publishProductDto.getName())
                .description(publishProductDto.getDescription())
                .price(publishProductDto.getPrice())
                .quantity(publishProductDto.getQuantity())
                .type(publishProductDto.getType())
                .customer(user)
                .build();
    }

    public static ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .type(product.getType())
                .customerId(product.getCustomer().getId())
                .build();
    }

}
