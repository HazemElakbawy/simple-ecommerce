package com.ecommerce.E_Commerce.constants;

public enum FailureMessages {
  USER_NOT_FOUND("The user with %s: %s does not exist."),
  USER_ALREADY_EXISTS("The user with %s: %s already exists."),
  BAD_CREDENTIALS("Incorrect %s. Please check and try again."),
  UNAUTHORIZED_ACCESS("You do not have permission to access this resource.");
  
  private final String message;

  FailureMessages(String message) {
    this.message = message;
  }

  public String getMessage(Object... args) {
    return String.format(message, args);
  }
}
