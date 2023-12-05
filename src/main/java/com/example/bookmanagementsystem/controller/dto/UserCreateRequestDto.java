package com.example.bookmanagementsystem.controller.dto;

import com.example.bookmanagementsystem.entity.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserCreateRequestDto(
        @NotBlank
        String name,

        @NotNull
                @Valid
        Address address,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
        String phoneNumber
) {
}
