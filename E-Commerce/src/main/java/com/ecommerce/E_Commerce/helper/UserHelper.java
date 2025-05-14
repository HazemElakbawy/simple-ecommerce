package com.ecommerce.E_Commerce.helper;

import com.ecommerce.E_Commerce.constants.FailureMessages;
import com.ecommerce.E_Commerce.entity.User;
import com.ecommerce.E_Commerce.exceptions.custom.UnauthorizedAccessException;
import com.ecommerce.E_Commerce.exceptions.custom.UserAlreadyExistsException;
import com.ecommerce.E_Commerce.exceptions.custom.UserNotFoundException;
import com.ecommerce.E_Commerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserHelper {
  private final UserRepository userRepo;

  public Optional<User> findUserByIdentifier(String identifier) {
    return userRepo.findByEmail(identifier);
  }

  public void checkIfUserExists(String identifier) {
    findUserByIdentifier(identifier).ifPresent(_ -> {
      throw new UserAlreadyExistsException(FailureMessages.USER_ALREADY_EXISTS.getMessage("email", identifier));
    });
  }

  public User fetchUserOrThrow(String identifier) {
    return findUserByIdentifier(identifier)
        .orElseThrow(() -> new UserNotFoundException(FailureMessages.USER_NOT_FOUND.getMessage("email", identifier)));
  }

  public User fetchUserByIdOrThrow(Long userId) {
    return userRepo.findById(userId)
        .orElseThrow(() -> new UserNotFoundException(FailureMessages.USER_NOT_FOUND.getMessage("id", userId)));
  }

  public void verifyUserAuthority(String userId, String authUserId) {
    if (!userId.equals(authUserId)) {
      throw new UnauthorizedAccessException(FailureMessages.UNAUTHORIZED_ACCESS.getMessage());
    }
  }
}
