package com.ecommerce.E_Commerce.service;

import com.ecommerce.E_Commerce.dto.response.UserResDto;
import com.ecommerce.E_Commerce.entity.User;
import com.ecommerce.E_Commerce.helper.UserHelper;
import com.ecommerce.E_Commerce.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserMapper userMapper;
  private final UserHelper userHelper;

  public UserResDto getUserById(String authUserId) {
    User user = userHelper.fetchUserByIdOrThrow(Long.valueOf(authUserId));
    return userMapper.toUserDto(user);
  }
}
