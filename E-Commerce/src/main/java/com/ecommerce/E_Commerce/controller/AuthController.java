package com.ecommerce.E_Commerce.controller;

import com.ecommerce.E_Commerce.config.AppProperties;
import com.ecommerce.E_Commerce.constants.SuccessMessages;
import com.ecommerce.E_Commerce.dto.ApiResponse;
import com.ecommerce.E_Commerce.dto.request.SignInReqDto;
import com.ecommerce.E_Commerce.dto.request.SignUpReqDto;
import com.ecommerce.E_Commerce.dto.response.TokensResDto;
import com.ecommerce.E_Commerce.dto.response.UserResDto;
import com.ecommerce.E_Commerce.service.AuthService;
import com.ecommerce.E_Commerce.service.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ecommerce.E_Commerce.helper.SuccessResponseHelper.buildResponse;

@RestController
@RequestMapping("/api/${app.api.version}/auth")
@RequiredArgsConstructor
public class AuthController {
  private final JWTService jwtService;
  private final AuthService authService;
  private final AppProperties properties;

  @PostMapping("/sign-up")
  public ResponseEntity<ApiResponse<UserResDto>> signUpUser(
      @Validated @RequestBody SignUpReqDto signUpReqDto,
      HttpServletRequest request) {

    UserResDto userResDto = authService.signUpUser(signUpReqDto);
    return buildResponse(properties.api().version(), request, HttpStatus.CREATED, userResDto, SuccessMessages.SIGN_UP_MSG.message);
  }

  @PostMapping("/sign-in")
  public ResponseEntity<ApiResponse<TokensResDto>> signIn(
      @Validated @RequestBody SignInReqDto signInReqDto,
      HttpServletRequest request) {

    TokensResDto responseDTO = authService.signIn(signInReqDto);
    return buildResponse(properties.api().version(), request, HttpStatus.OK, responseDTO, SuccessMessages.SIGN_IN_MSG.message);
  }
}
