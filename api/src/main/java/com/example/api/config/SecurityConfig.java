package com.example.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.api.services.UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        // @Autowired
        // JwtConfig jwtConfig;

        @Autowired
        PasswordConfig passwordConfig;

        // @Autowired
        // UserService userService;

        @Bean
        AuthenticationProvider authenticationProvider() {
                DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
                // authenticationProvider.setUserDetailsService();
                authenticationProvider.setPasswordEncoder(passwordConfig.passwordEncorder());
                return authenticationProvider;
        }

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf
                                                .disable())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers(HttpMethod.POST, "/api/v1/signup", "/api/v1/signin")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/v1/test/**").permitAll()
                                                .anyRequest().authenticated())
                                .authenticationProvider(authenticationProvider());
                // .addFilterBefore(jwtAuthenticationFilter,
                // UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }

}
