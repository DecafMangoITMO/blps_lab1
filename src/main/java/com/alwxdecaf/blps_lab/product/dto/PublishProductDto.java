package com.alwxdecaf.blps_lab.product.dto;

import com.alwxdecaf.blps_lab.product.model.ProductType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublishProductDto {

    @NotBlank(message = "name can not be blank")
    private final String name;

    @NotBlank(message = "description can not be blank")
    private final String description;

    @PositiveOrZero(message = "price can not be negative")
    private final double price;

    @PositiveOrZero(message = "quantity can not be negative")
    private final int quantity;

    @NotNull
    private final ProductType type;
}
