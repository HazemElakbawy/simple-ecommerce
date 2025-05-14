package com.ecommerce.E_Commerce.mapper;

import com.ecommerce.E_Commerce.dto.request.SignUpReqDto;
import com.ecommerce.E_Commerce.dto.response.UserResDto;
import com.ecommerce.E_Commerce.entity.User;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
  UserResDto toUserDto(User user);

  @Mapping(target = "password", expression = "java(passwordEncoder.encode(signUpReqDto.password()))")
  User toUserEntity(SignUpReqDto signUpReqDto, @Context PasswordEncoder passwordEncoder);
}
