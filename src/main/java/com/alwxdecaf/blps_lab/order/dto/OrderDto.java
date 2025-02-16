package com.alwxdecaf.blps_lab.order.dto;

import com.alwxdecaf.blps_lab.order.model.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {

    private final long id;

    private final String city;

    private final OrderStatus status;

    private final int quantity;

    @JsonProperty("customer_id")
    private final long customerId;

    @JsonProperty("user_id")
    private final long userId;

    @JsonProperty("product_id")
    private final long productId;

}
