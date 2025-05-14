package com.ecommerce.E_Commerce.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CartItemReqDto(
    @NotNull Long productId,
    @Min(1) Integer quantity
) {
}
