package com.ecommerce.E_Commerce.service;

import com.ecommerce.E_Commerce.config.AppProperties;
import com.ecommerce.E_Commerce.entity.User;
import com.ecommerce.E_Commerce.helper.JwtHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JWTService {
  private final AppProperties properties;
  private final JwtHelper jwtHelper;

  public String generateAccessToken(User user) {
    return jwtHelper.generateToken(user, properties.jwt().accessTokenTtL());
  }

  public String getAccessTokenDuration() {
    return properties.jwt().accessTokenTtL() / (1000 * 60) + " minutes";
  }

  public String getTokenFromHeader(String authHeader) {
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      throw new IllegalArgumentException("Invalid Authorization header format");
    }

    return authHeader.split(" ")[1];
  }

  public String getUserIdFromToken(String token) {
    return jwtHelper.extractUserId(token);
  }
}
