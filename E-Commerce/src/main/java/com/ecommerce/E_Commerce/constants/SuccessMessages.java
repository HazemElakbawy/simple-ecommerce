package com.ecommerce.E_Commerce.constants;

public enum SuccessMessages {
  SIGN_UP_MSG("User registration is successful."),
  SIGN_IN_MSG("Welcome back! You have successfully logged in."),
  GET_USER_MSG("User details are retrieved successfully."),

  CREATE_PRODUCT_MSG("Product created successfully"),
  GET_PRODUCT_MSG("Product fetched successfully"),
  UPDATE_PRODUCT_MSG("Product updated successfully"),
  DELETE_PRODUCT_MSG("Product deleted successfully"),
  FILTER_PRODUCTS_MSG("Products filtered successfully"),

  GET_CART_MSG("Cart retrieved successfully"),
  ADD_CART_ITEM_MSG("Item added to cart successfully"),
  REMOVE_CART_ITEM_MSG("Item removed from cart successfully");

  public final String message;

  SuccessMessages(String message) {
    this.message = message;
  }
}
