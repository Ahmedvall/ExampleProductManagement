package com.example.example_product_management.security;

import com.example.example_product_management.security.filters.CustomKeyFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Value("${app.secret}")
    private String key;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.httpBasic()
                .and()
                .addFilterBefore(new CustomKeyFilter(key), BasicAuthenticationFilter.class)
                .authorizeRequests() //.anyRequest().authenticated()
                .mvcMatchers(HttpMethod.GET, "/users").hasAuthority("admin_read")
                .mvcMatchers(HttpMethod.POST, "/users").hasAuthority("admin_write")
                .mvcMatchers(HttpMethod.GET, "/products").hasAuthority("client_read")
                .mvcMatchers(HttpMethod.POST, "/products").hasAuthority("client_write")
                .mvcMatchers(HttpMethod.DELETE, "/products").hasAuthority("client_write")
                .and()
                .csrf().disable()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
