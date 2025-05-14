package com.ecommerce.E_Commerce.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignInReqDto(
    @NotBlank(message = "email is required")
    @Size(max = 100, message = "email must be at least 8 characters long")
    @Email(message = "Invalid email format")
    String email,

    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters long")
    String password
) {
}
