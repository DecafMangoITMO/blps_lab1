package com.alwxdecaf.blps_lab.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterUserDto {

    @JsonProperty("first_name")
    @NotBlank(message = "first_name can not be blank")
    @Pattern(regexp = "^[a-zA-Z]+$|^[а-яА-ЯёЁ]+$", message = "first_name can must contain only letters")
    private final String firstName;

    @JsonProperty("last_name")
    @NotBlank(message = "last_name can not be blank")
    @Pattern(regexp = "^[a-zA-Z]+$|^[а-яА-ЯёЁ]+$", message = "last_name can must contain only letters")
    private final String lastName;

    @PositiveOrZero(message = "balance can not be negative")
    private final double balance;

}
