package com.ecommerce.E_Commerce.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app")
public record AppProperties(Api api, JWT jwt, Cloudinary cloudinary) {

  public record Api(@NotBlank String version) {
  }

  public record JWT(
      @NotBlank String secret,
      long accessTokenTtL) {
  }

  public record Cloudinary(
      @NotBlank String cloudName,
      @NotBlank String apiKey,
      @NotBlank String apiSecret) {
  }
}
