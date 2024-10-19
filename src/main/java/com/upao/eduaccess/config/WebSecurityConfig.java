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
//                        .requestMatchers("/auth/*", "/swagger-ui/", "/v3/api-docs/*").permitAll()
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
                .cors(Customizer.withDefaults())  // Habilitar CORS si es necesario
                .csrf(csrf -> csrf.disable())  // Deshabilitar CSRF si no es necesario
                .authorizeHttpRequests(auth -> auth
                        // Permitir acceso a Swagger y API Docs sin autenticación
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/v3/api-docs.yaml", "/swagger-ui.html").permitAll()
                        // Permitir acceso sin autenticación al endpoint de registro y a Swagger
                        .requestMatchers("/registrar/**").permitAll()  // Permitir todos los sub-endpoints de registrar
                        // Cualquier otra solicitud requerirá autenticación
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(httpBasic -> httpBasic.disable())  // Deshabilitar autenticación básica
                .formLogin(formLogin -> formLogin.disable());  // Deshabilitar login basado en formularios


        return http.build();
    }
}