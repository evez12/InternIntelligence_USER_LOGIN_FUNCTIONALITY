package com.huseynov.security.security;

import com.huseynov.security.common.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
public class MyUserDetailServiceImpl implements MyUserDetailService {

    private final MyUserRepo userRepo;

    public MyUserDetailServiceImpl(MyUserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {

        log.info("My custom class's method loadUserByUsername called....");
        MyUser user = userRepo.findByUsername(username)
                .orElseThrow(() -> {
                            log.warn("Not found user with username, {}", username);
                            return new RuntimeException("Invalid username or password");
                        }
                );

        MyUserDTO userDTO = UserMapper.convertEntityToDTO(user);
        log.info("user convert to userDTO {}", userDTO);
        return new User(userDTO.getUsername(),
                userDTO.getPassword(),
                mapRolesToAuthorities(userDTO.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        log.info("mapRolesToAuthorities called");
        roles.forEach(role -> log.info("role: {}", role));
        return roles
                .stream()// "ROLE_"+
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }
}
