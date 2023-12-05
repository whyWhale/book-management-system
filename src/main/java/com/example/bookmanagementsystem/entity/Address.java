package com.example.bookmanagementsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record Address(
		@NotBlank
		@Column(nullable = false)
		String city,

		@NotBlank
		@Column(nullable = false)
		String street,

		@NotBlank
		@Column(nullable = false)
		String zipCode,

		@NotBlank
		@Column(nullable = false)
		String detail){
}
