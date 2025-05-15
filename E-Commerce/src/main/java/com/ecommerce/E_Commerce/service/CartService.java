package com.ecommerce.E_Commerce.service;

import com.ecommerce.E_Commerce.dto.request.CartItemReqDto;
import com.ecommerce.E_Commerce.dto.response.CartItemResDto;
import com.ecommerce.E_Commerce.dto.response.CartResDto;
import com.ecommerce.E_Commerce.entity.Cart;
import com.ecommerce.E_Commerce.entity.CartItem;
import com.ecommerce.E_Commerce.entity.Product;
import com.ecommerce.E_Commerce.entity.User;
import com.ecommerce.E_Commerce.helper.UserHelper;
import com.ecommerce.E_Commerce.repository.CartItemRepository;
import com.ecommerce.E_Commerce.repository.CartRepository;
import com.ecommerce.E_Commerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final ProductRepository productRepository;
  private final UserHelper userHelper;

  @Transactional
  public CartResDto addItem(Long userId, CartItemReqDto req) {
    User user = userHelper.fetchUserByIdOrThrow(userId);
    Product product = productRepository.findById(req.productId())
        .orElseThrow(() -> new IllegalArgumentException("Product not found"));

    Cart cart = cartRepository.findByUserId(userId)
        .orElseGet(
            () -> cartRepository.save(Cart.builder()
                .user(user)
                .items(new ArrayList<>())
                .build()));

    Optional<CartItem> existing = cart.getItems().stream()
        .filter(item -> item.getProduct().getId().equals(req.productId()))
        .findFirst();

    if (existing.isPresent()) {
      CartItem item = existing.get();
      item.setQuantity(item.getQuantity() + req.quantity());
    } else {
      CartItem newItem = CartItem.builder()
          .cart(cart)
          .product(product)
          .quantity(req.quantity())
          .build();
      cart.getItems().add(newItem);
    }

    Cart updatedCart = cartRepository.save(cart);
    return buildCartDto(updatedCart);
  }

  @Transactional
  public CartResDto removeItem(Long userId, Long productId) {
    Cart cart = cartRepository.findByUserId(userId)
        .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

    CartItem item = cart.getItems().stream()
        .filter(i -> i.getProduct().getId().equals(productId))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Product not in cart"));

    if (item.getQuantity() > 1) {
      item.setQuantity(item.getQuantity() - 1);
    } else {
      cart.getItems().remove(item);
      cartItemRepository.delete(item);
    }

    Cart updatedCart = cartRepository.save(cart);
    return buildCartDto(updatedCart);
  }


  public CartResDto getCartByUserId(Long userId) {
    Cart cart = cartRepository.findByUserIdWithItems(userId)
        .orElseGet(
            () -> Cart.builder()
                .user(userHelper.fetchUserByIdOrThrow(userId))
                .items(new ArrayList<>())
                .build());
    return buildCartDto(cart);
  }

  public void clearCart(Long userId) {
    Cart cart = cartRepository.findByUserIdWithItems(userId)
        .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
    cart.getItems().clear();
    cartRepository.save(cart);
  }

  private CartResDto buildCartDto(Cart cart) {
    List<CartItemResDto> items = cart.getItems().stream()
        .map(item -> new CartItemResDto(
            item.getProduct().getId(),
            item.getProduct().getName(),
            item.getQuantity(),
            item.getProduct().getPrice(),
            item.getProduct().getPictureUrl()
        ))
        .collect(Collectors.toList());

    BigDecimal total = items.stream()
        .map(i -> i.price().multiply(BigDecimal.valueOf(i.quantity())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    return new CartResDto(cart.getUser().getId(), items, total);
  }


}
