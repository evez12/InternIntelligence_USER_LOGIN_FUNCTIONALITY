package com.huseynov.security.security;

import com.huseynov.security.exception.CustomAuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class AuthTokenFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    public AuthTokenFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }


    //    Filters incoming requests and sets the authentication in the security context if a valid JWT token is found.
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        log.info("AuthTokenFilter called ");
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUsernameFromToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication = new
                        UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                log.info("Roles from jwt: {}", userDetails.getAuthorities());

                authentication
                        .setDetails(new
                                WebAuthenticationDetailsSource()
                                .buildDetails(request)
                        );

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);
            }
        } catch (Exception e) {
            log.error("Failed during Set authentication to user(doFilterInternal): {}", e.getMessage());
            throw new CustomAuthenticationException("Failed during check jwt token");
        }
        filterChain.doFilter(request, response);
    }

    public String parseJwt(HttpServletRequest request) {
        String jwt = jwtUtils.getJwtFromHeader(request);
        log.info("Jwt parsed: {}", jwt);
        return jwt;
    }
}
