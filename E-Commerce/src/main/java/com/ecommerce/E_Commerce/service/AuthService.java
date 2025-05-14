package com.ecommerce.E_Commerce.service;

import com.ecommerce.E_Commerce.constants.FailureMessages;
import com.ecommerce.E_Commerce.dto.request.SignInReqDto;
import com.ecommerce.E_Commerce.dto.request.SignUpReqDto;
import com.ecommerce.E_Commerce.dto.response.TokensResDto;
import com.ecommerce.E_Commerce.dto.response.UserResDto;
import com.ecommerce.E_Commerce.entity.User;
import com.ecommerce.E_Commerce.helper.UserHelper;
import com.ecommerce.E_Commerce.mapper.TokenMapper;
import com.ecommerce.E_Commerce.mapper.UserMapper;
import com.ecommerce.E_Commerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepo;
  private final PasswordEncoder passwordEncoder;
  private final TokenMapper tokenMapper;
  private final UserMapper userMapper;
  private final JWTService jwtService;
  private final UserHelper userHelper;

  public UserResDto signUpUser(SignUpReqDto signUpReqDto) {
    userHelper.checkIfUserExists(signUpReqDto.email());
    User user = userMapper.toUserEntity(signUpReqDto, passwordEncoder);
    user = userRepo.save(user);
    return new UserResDto(user.getFullName(), user.getEmail());
  }

  public TokensResDto signIn(SignInReqDto signInReqDto) {
    User user = userHelper.fetchUserOrThrow(signInReqDto.email());
    if (!passwordEncoder.matches(signInReqDto.password(), user.getPassword())) {
      throw new BadCredentialsException(FailureMessages.BAD_CREDENTIALS.getMessage("password"));
    }
    return tokenMapper.toTokensDto(user, jwtService);
  }
}
