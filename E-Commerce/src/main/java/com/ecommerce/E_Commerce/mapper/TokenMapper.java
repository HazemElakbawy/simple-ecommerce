package com.ecommerce.E_Commerce.mapper;

import com.ecommerce.E_Commerce.dto.response.TokensResDto;
import com.ecommerce.E_Commerce.entity.User;
import com.ecommerce.E_Commerce.service.JWTService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TokenMapper {

  @Mapping(target = "accessToken", expression = "java(jwtService.generateAccessToken(user))")
  @Mapping(target = "accessTokenExpiresIn", expression = "java(jwtService.getAccessTokenDuration())")
  @Mapping(target = "tokenType", constant = "Bearer")
  TokensResDto toTokensDto(User user, @Context JWTService jwtService);
}
