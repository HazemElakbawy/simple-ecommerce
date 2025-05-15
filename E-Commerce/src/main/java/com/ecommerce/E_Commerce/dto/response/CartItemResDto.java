package com.ecommerce.E_Commerce.dto.response;

import java.math.BigDecimal;

public record CartItemResDto(
    Long id,
    String name,
    Integer quantity,
    BigDecimal price,
    String pictureUrl
) {
}
