package com.alwxdecaf.blps_lab.product.dto;

import com.alwxdecaf.blps_lab.product.model.ProductType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {

    private long id;

    private final String name;

    private final String description;

    private final double price;

    private final int quantity;

    private final ProductType type;

    @JsonProperty("customer_id")
    private long customerId;

}
