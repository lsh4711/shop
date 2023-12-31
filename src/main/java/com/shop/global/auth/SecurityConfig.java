package com.shop.global.auth;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.shop.global.auth.filter.CustomFilterConfigurer;
import com.shop.global.auth.handler.JwtAccessDeniedHandler;
import com.shop.global.auth.handler.JwtAuthenticationEntryPoint;
import com.shop.global.auth.jwt.JwtTokenizer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenizer jwtTokenizer;

    private final String[] accessForGuest = {
        // doc
        "/",
        "/docs/**",
        "/v3/api-docs/swagger-config",
        //
        "/*/members/register",
        "/*/marts/public/**",
        "/*/items",
        "/*/items/*",
        "/*/items/*/price/histories/**",
        "/**/images/thumbnail"
    };

    private final String[] accessForUser = {
        // "/*/members/cart/**"
        "/*/members/**",
        "/*/coupons/**",
        "/*/orders/pay",
        "/*/orders",
        "/*/orders/*"
    };

    private final String[] accessForSeller = {
        "/*/members/**",
        "/*/marts/**",
        "/*/items/**",
        "/*/products/**",
        "/*/categories/**",
        "/*/brands/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors(withDefaults())
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                .accessDeniedHandler(new JwtAccessDeniedHandler())
                .and()
                .apply(new CustomFilterConfigurer(jwtTokenizer))
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/h2/**").permitAll()
                        .antMatchers(accessForGuest).permitAll()
                        .antMatchers(accessForUser).hasRole("USER")
                        .antMatchers(accessForSeller).hasRole("SELLER")
                        .anyRequest().denyAll())
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
            "https://teamdev.shop:7777",
            "https://lsh4711-shop.netlify.app",
            "http://localhost:7777",
            "http://localhost:5173",
            "http://localhost:4173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
