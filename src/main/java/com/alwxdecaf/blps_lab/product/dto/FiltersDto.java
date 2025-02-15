package com.alwxdecaf.blps_lab.product.dto;

import com.alwxdecaf.blps_lab.product.model.ProductType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FiltersDto {

    private String name;

    @PositiveOrZero(message = "price can not be negative")
    private Double price;
    
    @PositiveOrZero(message = "quantity can not be negative")
    private Integer quantity;
    
    private ProductType type;
}

