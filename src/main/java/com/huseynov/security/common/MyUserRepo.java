package com.huseynov.security.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyUserRepo extends JpaRepository<MyUser, Long> {

    @Query("Select u from MyUser u join fetch u.roles where u.username=:username")
    Optional<MyUser> findByUsername(String username);

    boolean existsByUsername(String username);


}
