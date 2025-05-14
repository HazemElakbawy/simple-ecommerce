package com.ecommerce.E_Commerce.repository;

import com.ecommerce.E_Commerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
