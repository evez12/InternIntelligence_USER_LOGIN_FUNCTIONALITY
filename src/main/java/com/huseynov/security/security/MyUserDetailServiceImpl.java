package com.huseynov.security.security;

import com.huseynov.security.common.MyUser;
import com.huseynov.security.common.MyUserRepo;
import com.huseynov.security.common.Role;
import com.huseynov.security.dto.MyUserDTO;
import com.huseynov.security.util.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
public class MyUserDetailServiceImpl implements UserDetailsService {

    private final MyUserRepo userRepo;

    public MyUserDetailServiceImpl(MyUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        log.info("loadUserByUsername have been called....");
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
