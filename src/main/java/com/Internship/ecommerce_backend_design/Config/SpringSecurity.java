package com.Internship.ecommerce_backend_design.Config;

import com.Internship.ecommerce_backend_design.Filter.JwtFilter;
import com.Internship.ecommerce_backend_design.Service.UserDetailService;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SpringSecurity {
    @Autowired
    JwtFilter jwtFilter;
    @Autowired
    UserDetailService  userDetailService;
    @Bean
    public SecurityFilterChain SpringSecurityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests.requestMatchers(
                        "/product/**",
                        "/ProductDetail/**"
                        ).authenticated().
                        requestMatchers(
                                "/Public/**"
                        ).permitAll().
                        requestMatchers("/admin/**").hasRole("ADMIN").
                        anyRequest().authenticated());
        http.sessionManagement(
        sessionManagement ->
        sessionManagement.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS));
        http.csrf(AbstractHttpConfigurer::disable);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.authenticationProvider(authenticationProvider());

     return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
