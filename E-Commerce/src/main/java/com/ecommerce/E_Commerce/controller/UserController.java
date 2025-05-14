package com.ecommerce.E_Commerce.controller;

import com.ecommerce.E_Commerce.config.AppProperties;
import com.ecommerce.E_Commerce.constants.SuccessMessages;
import com.ecommerce.E_Commerce.dto.ApiResponse;
import com.ecommerce.E_Commerce.dto.response.UserResDto;
import com.ecommerce.E_Commerce.service.JWTService;
import com.ecommerce.E_Commerce.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ecommerce.E_Commerce.helper.SuccessResponseHelper.buildResponse;

@RestController
@RequestMapping("/api/${app.api.version}/users")
@RequiredArgsConstructor
public class UserController {
  private final JWTService jwtService;
  private final UserService userService;
  private final AppProperties properties;

  private String getIdFromAuthHeader(String authHeader) {
    String token = jwtService.getTokenFromHeader(authHeader);
    return jwtService.getUserIdFromToken(token);
  }

  @GetMapping
  public ResponseEntity<ApiResponse<UserResDto>> getUserById(
      @RequestHeader("Authorization") String authHeader,
      HttpServletRequest request) {

    UserResDto userResDto = userService.getUserById(getIdFromAuthHeader(authHeader));
    return buildResponse(properties.api().version(), request, HttpStatus.OK, userResDto, SuccessMessages.GET_USER_MSG.message);
  }
}
