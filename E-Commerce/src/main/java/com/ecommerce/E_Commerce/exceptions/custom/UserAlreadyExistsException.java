package com.ecommerce.E_Commerce.exceptions.custom;

public class UserAlreadyExistsException extends RuntimeException {
  public UserAlreadyExistsException(String message) {
    super(message);
  }
}
