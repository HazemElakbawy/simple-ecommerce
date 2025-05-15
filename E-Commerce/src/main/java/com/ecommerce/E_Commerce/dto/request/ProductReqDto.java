package com.ecommerce.E_Commerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductReqDto(
    @NotBlank(message = "Product name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    String name,

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description must not exceed 500 characters")
    String description,

    @Size(max = 255, message = "Picture URL must not exceed 255 characters")
    String pictureUrl,

    @Size(max = 50, message = "Size must not exceed 50 characters")
    String size,

    @Size(max = 50, message = "Color must not exceed 50 characters")
    String color,

    @NotNull(message = "Category name is required")
    String categoryName,

    @NotNull(message = "Price is required")
    Double price
) {
}
