package com.ecommerce.E_Commerce.dto.response;

public record ProductResDto(
    Long id,
    String name,
    String description,
    String pictureUrl,
    String size,
    String color,
    String price,
    Long categoryId,
    String categoryName
) {
}
