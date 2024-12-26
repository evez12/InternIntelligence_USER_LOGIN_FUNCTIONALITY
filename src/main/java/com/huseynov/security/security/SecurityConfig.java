package com.huseynov.security.security;

import com.huseynov.security.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // For @PreAuthorize work
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(request ->
                request
                        .requestMatchers("/hello/**").permitAll()
                        .requestMatchers("/user/**").hasAuthority("USER")
                        .requestMatchers("/manager/**").hasAuthority("MANAGER")
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/register/**").permitAll()
                        .anyRequest().authenticated()
        );

        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());

        http.csrf(AbstractHttpConfigurer::disable);  // csrf disabled

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // default strength = 10
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
}
