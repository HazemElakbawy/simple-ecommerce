package com.ecommerce.E_Commerce.helper;

import com.ecommerce.E_Commerce.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtHelper {
  private final JwtEncoder jwtEncoder;
  private final JwtDecoder jwtDecoder;

  public String generateToken(User user, long ttlInMillis) {
    JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();
    Instant now = Instant.now();
    JwtClaimsSet jwtClaims = JwtClaimsSet.builder()
        .issuer("user-service")
        .subject(String.valueOf(user.getId()))
        .issuedAt(now)
        .expiresAt(Date.from(now).toInstant().plusMillis(ttlInMillis))
        .build();
    return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, jwtClaims)).getTokenValue();
  }

  public <T> T extractClaim(String token, String claim) {
    Jwt jwt = jwtDecoder.decode(token);
    return jwt.getClaim(claim);
  }

  public String extractUserId(String token) {
    return extractClaim(token, "sub");
  }
}
