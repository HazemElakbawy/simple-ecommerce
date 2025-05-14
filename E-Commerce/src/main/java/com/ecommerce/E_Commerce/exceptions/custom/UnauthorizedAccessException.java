package com.ecommerce.E_Commerce.exceptions.custom;

public class UnauthorizedAccessException extends RuntimeException {
  public UnauthorizedAccessException(String message) {
    super(message);
  }
}
