package com.huseynov.security.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huseynov.security.dto.ApiResponse;
import com.huseynov.security.dto.ErrorDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;


@Configuration
@Slf4j
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    // this method is used to handle unauthorized requests
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        log.error("Unauthorized :{}", authException.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ApiResponse<String> body = new ApiResponse<>();
        body.setStatus(HttpStatus.UNAUTHORIZED.toString());
        body.setErrors(
                new ErrorDTO(authException.getMessage())
        );
        body.setResults("path: " + request.getServletPath());

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }
}
