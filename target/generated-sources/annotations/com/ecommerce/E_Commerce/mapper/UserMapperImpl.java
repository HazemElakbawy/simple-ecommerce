package com.ecommerce.E_Commerce.mapper;

import com.ecommerce.E_Commerce.dto.request.SignUpReqDto;
import com.ecommerce.E_Commerce.dto.response.UserResDto;
import com.ecommerce.E_Commerce.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-15T14:02:38+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        String fullName = null;
        String email = null;

        fullName = user.getFullName();
        email = user.getEmail();

        UserResDto userResDto = new UserResDto( fullName, email );

        return userResDto;
    }

    @Override
    public User toUserEntity(SignUpReqDto signUpReqDto, PasswordEncoder passwordEncoder) {
        if ( signUpReqDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.fullName( signUpReqDto.fullName() );
        user.email( signUpReqDto.email() );

        user.password( passwordEncoder.encode(signUpReqDto.password()) );

        return user.build();
    }
}
