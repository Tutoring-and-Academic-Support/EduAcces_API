package com.upao.eduaccess.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.web.DefaultSecurityFilterChain;

@RequiredArgsConstructor
public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenProvider tokenProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JWTFilter jwtFilter = new JWTFilter(tokenProvider);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
