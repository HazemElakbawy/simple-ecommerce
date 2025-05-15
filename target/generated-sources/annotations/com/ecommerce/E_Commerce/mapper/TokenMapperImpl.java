package com.ecommerce.E_Commerce.mapper;

import com.ecommerce.E_Commerce.dto.response.TokensResDto;
import com.ecommerce.E_Commerce.entity.User;
import com.ecommerce.E_Commerce.service.JWTService;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-15T14:02:37+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class TokenMapperImpl implements TokenMapper {

    @Override
    public TokensResDto toTokensDto(User user, JWTService jwtService) {
        if ( user == null ) {
            return null;
        }

        String accessToken = jwtService.generateAccessToken(user);
        String accessTokenExpiresIn = jwtService.getAccessTokenDuration();
        String tokenType = "Bearer";

        TokensResDto tokensResDto = new TokensResDto( accessToken, accessTokenExpiresIn, tokenType );

        return tokensResDto;
    }
}
