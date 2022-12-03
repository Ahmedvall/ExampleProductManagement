package com.example.example_product_management.security.provider;

import com.example.example_product_management.security.authentication.CustomAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@RequiredArgsConstructor
public class CustomKeyProvider implements AuthenticationProvider {

    private final String key;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        CustomAuthentication ca = (CustomAuthentication) authentication;
        if (key.equals(ca.getKey())) {
            ca.setAuthenticated(true);
            return ca;
        }

        throw  new BadCredentialsException("Authentication Failed :(");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.equals(authentication);
    }
}
