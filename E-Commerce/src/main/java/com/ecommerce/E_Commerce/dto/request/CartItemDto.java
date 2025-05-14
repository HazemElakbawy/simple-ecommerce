package com.ecommerce.E_Commerce.dto.request;

import java.math.BigDecimal;

public record CartItemDto(
    Long productId,
    String name,
    Integer quantity,
    BigDecimal unitPrice
) {
}
