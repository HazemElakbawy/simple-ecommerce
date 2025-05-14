package com.ecommerce.E_Commerce.controller;

import com.ecommerce.E_Commerce.config.AppProperties;
import com.ecommerce.E_Commerce.constants.SuccessMessages;
import com.ecommerce.E_Commerce.dto.ApiResponse;
import com.ecommerce.E_Commerce.dto.request.CartItemReqDto;
import com.ecommerce.E_Commerce.dto.response.CartResDto;
import com.ecommerce.E_Commerce.service.CartService;
import com.ecommerce.E_Commerce.service.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ecommerce.E_Commerce.helper.SuccessResponseHelper.buildResponse;

@RestController
@RequestMapping("/api/${app.api.version}/cart")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;
  private final JWTService jwtService;
  private final AppProperties properties;

  private Long getUserId(String authHeader) {
    String token = jwtService.getTokenFromHeader(authHeader);
    return Long.valueOf(jwtService.getUserIdFromToken(token));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<CartResDto>> getCart(
      @RequestHeader("Authorization") String authHeader,
      HttpServletRequest request) {

    Long userId = getUserId(authHeader);
    CartResDto cartDto = cartService.getCartByUserId(userId);
    return buildResponse(
        properties.api().version(),
        request,
        HttpStatus.OK,
        cartDto,
        SuccessMessages.GET_CART_MSG.message
    );
  }

  @PostMapping("/items")
  public ResponseEntity<ApiResponse<CartResDto>> addItem(
      @RequestHeader("Authorization") String authHeader,
      @RequestBody CartItemReqDto req,
      HttpServletRequest request) {

    Long userId = getUserId(authHeader);
    CartItemReqDto cartItemReqDto = new CartItemReqDto(req.productId(), req.quantity());
    CartResDto updated = cartService.addItem(userId, cartItemReqDto);
    return buildResponse(
        properties.api().version(),
        request,
        HttpStatus.OK,
        updated,
        SuccessMessages.ADD_CART_ITEM_MSG.message
    );
  }

  @DeleteMapping("/items/{productId}")
  public ResponseEntity<ApiResponse<CartResDto>> removeItem(
      @RequestHeader("Authorization") String authHeader,
      @PathVariable Long productId,
      HttpServletRequest request) {

    Long userId = getUserId(authHeader);
    CartResDto updated = cartService.removeItem(userId, productId);
    return buildResponse(
        properties.api().version(),
        request,
        HttpStatus.OK,
        updated,
        SuccessMessages.REMOVE_CART_ITEM_MSG.message
    );
  }
}
