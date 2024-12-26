package com.huseynov.security.service;

import com.huseynov.security.dto.CreateUserRequest;
import com.huseynov.security.dto.MyUserDTO;
import com.huseynov.security.dto.UserResponse;
import com.huseynov.security.exception.ExistsUsernameException;
import com.huseynov.security.model.MyUser;
import com.huseynov.security.model.Role;
import com.huseynov.security.repo.MyUserRepo;
import com.huseynov.security.repo.RoleRepo;
import com.huseynov.security.util.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final MyUserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserResponse createUser(CreateUserRequest request) {

        if (userRepo.existsByUsername(request.getUsername())) {
            log.info("exists {}", request.getUsername());
            throw new ExistsUsernameException("Username already exists, " + request.getUsername());
        }

        MyUser user = UserMapper.convertCreateUserToEntity(request);
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        // Default role user
        Role role1 = roleRepo.findByName("USER");
        user.setRoles(Set.of(role1));
        MyUser savedUser = userRepo.save(user);

        return UserMapper
                .convertEntityToResponseUser(savedUser);
    }


    @Override
    public UserDetails loadUserByUsername(String username) {

        log.info("loadUserByUsername have been called....");
        MyUser user = userRepo.findByUsername(username)
                .orElseThrow(() -> {
                            log.warn("Not found: {}", username);
                            return new UsernameNotFoundException("Invalid username or password");
                        }
                );

        MyUserDTO userDTO = UserMapper.convertEntityToDTO(user);

        log.info("userDTO: {}", userDTO);
        return new User(userDTO.getUsername(),
                userDTO.getPassword(),
                mapRolesToAuthorities(userDTO.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        log.info("mapRolesToAuthorities called");
        roles.forEach(role -> log.info("role: {}", role));
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }
}
