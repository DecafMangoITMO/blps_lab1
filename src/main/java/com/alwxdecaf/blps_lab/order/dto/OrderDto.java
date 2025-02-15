package com.alwxdecaf.blps_lab.order.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {
    @NotBlank(message = "city can not be blank")
    private String city;

    @NotBlank(message = "street can not be blank")
    private String street;

    @NotEmpty(message = "products can not be empty")
    private List<Long> products;

}
