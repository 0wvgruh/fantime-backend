package com.fantime.fantime_backend.config;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        // .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // 모든 api 요청 허용
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/api/auth/register",
                "/api/auth/login"
            ).permitAll()

            .requestMatchers(HttpMethod.POST, "/api/posts").authenticated()
            .requestMatchers(HttpMethod.POST, "/api/posts/**").authenticated()

            .requestMatchers("/api/admin/**").hasRole("ADMIN")
            .requestMatchers("/api/auth/current").authenticated()
            .anyRequest().permitAll()  
            // .authenticated() > 요청 인증 필요
            
        )
        .csrf(csrf -> csrf.disable())
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }
}
