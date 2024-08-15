package com.peytosoft.authenticationservice.config;

import com.peytosoft.authenticationservice.filters.JwtAuthenticationEntryPoint;
import com.peytosoft.authenticationservice.filters.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    public List<String> pubRequests = {"auth/**", "swagger-ui/**"};

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authorizeRequest ->
                        authorizeRequest
                                .requestMatchers("auth/**",
                                        "test/**",
                                        "/v3/api-docs",
                                        "/configuration/ui",
                                        "/swagger-resources/**",
                                        "/configuration/security",
                                        "/swagger-ui.html",
                                        "/webjars/**").permitAll()
                                .anyRequest().authenticated()

                )
//                .exceptionHandling(ex-> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .csrf(csrf->csrf.disable())
                .cors(cors->cors.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        ;

        return httpSecurity.build();


    }
}
