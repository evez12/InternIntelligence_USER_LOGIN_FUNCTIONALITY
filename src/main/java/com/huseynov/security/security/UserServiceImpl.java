package com.huseynov.security.security;

import com.huseynov.security.common.MyUser;
import com.huseynov.security.common.MyUserRepo;
import com.huseynov.security.common.Role;
import com.huseynov.security.common.RoleRepo;
import com.huseynov.security.dto.CreateUserRequest;
import com.huseynov.security.dto.ResponseUser;
import com.huseynov.security.exception.ExistsUsernameException;
import com.huseynov.security.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final MyUserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public ResponseUser createUser(CreateUserRequest request) {

        if (userRepo.existsByUsername(request.getUsername())) {
            throw new ExistsUsernameException("Username already exists, " + request.getUsername());
        }

        MyUser user = UserMapper.convertCreateUserToEntity(request);
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );


        // Default role user
        Role role1 = roleRepo.findByName("USER");
        Role role2 = roleRepo.findByName("MANAGER");
        user.setRoles(Set.of(role1, role2));
        MyUser savedUser = userRepo.save(user);

        return UserMapper
                .convertEntityToResponseUser(savedUser);
    }
}
