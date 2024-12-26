package com.huseynov.security.repo;

import com.huseynov.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);


}
