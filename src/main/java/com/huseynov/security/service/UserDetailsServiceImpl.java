package com.huseynov.security.service;

import com.huseynov.security.model.MyUser;
import com.huseynov.security.model.Role;
import com.huseynov.security.repo.MyUserRepo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MyUserRepo userRepo;

    public UserDetailsServiceImpl(MyUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {

        log.info("loadUserByUsername have been called....");
        MyUser user = userRepo.findByUsername(username)
                .orElseThrow(() -> {
                            log.warn("Not found: {}", username);
                            return new UsernameNotFoundException("Invalid username or password");
                        }
                );

        return new User(user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles())
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
