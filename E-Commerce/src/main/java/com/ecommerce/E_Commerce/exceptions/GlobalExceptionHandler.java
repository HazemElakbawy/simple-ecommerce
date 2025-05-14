package com.ecommerce.E_Commerce.exceptions;


import com.ecommerce.E_Commerce.config.AppProperties;
import com.ecommerce.E_Commerce.dto.ApiResponse;
import com.ecommerce.E_Commerce.exceptions.custom.UnauthorizedAccessException;
import com.ecommerce.E_Commerce.exceptions.custom.UserAlreadyExistsException;
import com.ecommerce.E_Commerce.exceptions.custom.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

import static com.ecommerce.E_Commerce.helper.FailureResponseHelper.buildResponse;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
  private final AppProperties properties;

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ApiResponse<ErrorDetails>> handleBadCredentialsException(BadCredentialsException e, HttpServletRequest request) {
    return buildResponse(properties.api().version(), request, HttpStatus.UNAUTHORIZED, e.getMessage(), "Authentication failed");
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ApiResponse<ErrorDetails>> handleUserNotFoundException(UserNotFoundException e, HttpServletRequest request) {
    return buildResponse(properties.api().version(), request, HttpStatus.NOT_FOUND, e.getMessage(), "User not found");
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<ApiResponse<ErrorDetails>> handleUserAlreadyExistsException(UserAlreadyExistsException e, HttpServletRequest request) {
    return buildResponse(properties.api().version(), request, HttpStatus.CONFLICT, e.getMessage(), "User already exists");
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ApiResponse<ErrorDetails>> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
    return buildResponse(properties.api().version(), request, HttpStatus.FORBIDDEN, e.getMessage(), "Access denied");
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<ErrorDetails>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
    BindingResult bindingResult = ex.getBindingResult();
    List<String> errorMessages = bindingResult.getAllErrors().stream()
        .map(ObjectError::getDefaultMessage)
        .collect(Collectors.toList());
    return buildResponse(properties.api().version(), request, HttpStatus.BAD_REQUEST, String.join(", ", errorMessages), "Validation failed");
  }

  @ExceptionHandler(UnauthorizedAccessException.class)
  public ResponseEntity<ApiResponse<ErrorDetails>> handleUnauthorizedAccessException(UnauthorizedAccessException e, HttpServletRequest request) {
    return buildResponse(properties.api().version(), request, HttpStatus.UNAUTHORIZED, e.getMessage(), "Unauthorized access");
  }
  
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ApiResponse<ErrorDetails>> handleDataIntegrityViolation(HttpServletRequest request) {
    return buildResponse(properties.api().version(), request, HttpStatus.CONFLICT, "An error occurred while processing your request. Please try again later.", "Data integrity issue");
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<ErrorDetails>> handleException(Exception e, HttpServletRequest request) {
    return buildResponse(properties.api().version(), request, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), "Internal server error");
  }
}