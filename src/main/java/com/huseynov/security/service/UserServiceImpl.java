package com.huseynov.security.service;

import com.huseynov.security.dto.*;
import com.huseynov.security.exception.CustomAuthenticationException;
import com.huseynov.security.exception.ExistsUsernameException;
import com.huseynov.security.model.MyUser;
import com.huseynov.security.model.Role;
import com.huseynov.security.repo.MyUserRepo;
import com.huseynov.security.repo.RoleRepo;
import com.huseynov.security.security.AuthService;
import com.huseynov.security.util.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final MyUserRepo userRepo;
    private final RoleRepo roleRepo;
    private final AuthService authService;

    @Override
    public RegisterResponse registerUser(CreateUserRequest request) {

        // if username exists in db
        if (userRepo.existsByUsername(request.getUsername())) {
            log.error("exists user: {}", request.getUsername());
            throw new ExistsUsernameException("Username already exists, " + request.getUsername());
        }

        MyUser user = UserMapper.convertCreateUserToEntity(request);
        user.setPassword(
                authService
                        .getPasswordEncoder()
                        .encode(request.getPassword())
        );

        // Default role user
        Role role1 = roleRepo.findByName("USER");
        user.setRoles(Set.of(role1));
        MyUser savedUser = userRepo.save(user); // save user to db
        log.debug("User saved: {}", savedUser);

        // Authenticate user and return username,token,roles
        LoginResponse loginResponse = authenticateUser(
                new LoginRequest(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        return new RegisterResponse(
                loginResponse.getUsername(),
                loginResponse.getJwtToken(),
                loginResponse.getRoles()
        );
    }

    @Override
    public LoginResponse authenticateUser(LoginRequest request) {
        Authentication authentication;
        try {
            // checking username and password with DaoAuthenticationProvider
            authentication = authService
                    .authentication(request.getUsername(), request.getPassword());
        } catch (AuthenticationException e) {
            log.error("Error in authentication {}", e.getMessage());
            throw new CustomAuthenticationException("Invalid username or password");
        }

        // Retrieve the authenticated user's details
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwtToken = authService.generateJwtToken(userDetails);
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return new LoginResponse(
                userDetails.getUsername(),
                jwtToken,
                roles
        );
    }

    @Override
    public UserProfileResponse getProfileDetails() {
        log.info("Get profile details");
        UserProfileResponse response = new UserProfileResponse();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        response.setUsername(userDetails.getUsername());
        response.setRoles(
                userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList()
        );

        return response;
    }
}
