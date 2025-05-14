package com.ecommerce.E_Commerce.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record CartResDto(
    Long userId,
    List<CartItemResDto> items,
    BigDecimal totalPrice
) {
}