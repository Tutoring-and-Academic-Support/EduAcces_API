package com.upao.eduaccess.config;

import com.upao.eduaccess.security.JWTConfigurer;
import com.upao.eduaccess.security.JWTFilter;
import com.upao.eduaccess.security.JwtAuthenticationEntryPoint;
import com.upao.eduaccess.security.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
//"""
// private final TokenProvider tokenProvider;
//    private final JWTFilter jwtRequestFilter;
//    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//
//    public WebSecurityConfig(TokenProvider tokenProvider, JWTFilter jwtRequestFilter, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
//        this.tokenProvider = tokenProvider;
//        this.jwtRequestFilter = jwtRequestFilter;
//        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .cors(Customizer.withDefaults())
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
//                        .anyRequest().authenticated())
//                .exceptionHandling(e -> e.authenticationEntryPoint(jwtAuthenticationEntryPoint))
//                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
// """
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Deshabilitar CSRF para evitar problemas en las pruebas
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()  // Permitir acceso a todas las solicitudes
                )
                .httpBasic(httpBasic -> httpBasic.disable())  // Deshabilitar autenticación básica
                .formLogin(formLogin -> formLogin.disable());  // Deshabilitar login basado en formularios

        return http.build();
    }
}
