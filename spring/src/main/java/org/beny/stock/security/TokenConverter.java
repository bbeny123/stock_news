package org.beny.stock.security;

import org.beny.stock.exception.StockException;
import org.beny.stock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.beny.stock.exception.StockError.INTERNAL_SERVER_ERROR;

@Component
public class TokenConverter extends JwtAccessTokenConverter {

    @Autowired
    private UserService userService;

    @Autowired
    public TokenConverter(@Value("${oauth.jwt.key:jwtKey}") String jwtKey) {
        super();
        this.setSigningKey(jwtKey);
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        accessToken = super.enhance(accessToken, authentication);
        if (authentication.getUserAuthentication() != null) {
            UserContext user = (UserContext) authentication.getPrincipal();
            Map<String, Object> additionalInfo = new HashMap<>();
            additionalInfo.put("type", user.getPrincipal().getType());
            additionalInfo.put("userId", user.getPrincipal().getId());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        }
        return accessToken;
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> claims) throws StockException {
        OAuth2Authentication authentication = super.extractAuthentication(claims);

        try {
            authentication = new OAuth2Authentication(authentication.getOAuth2Request(), new UserContext(userService.findByLogin((String) claims.get("user_name")), "N/A"));
        } catch (Exception ex) {
            throw INTERNAL_SERVER_ERROR.exception();
        }

        return authentication;
    }

}
