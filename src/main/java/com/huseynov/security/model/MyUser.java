package com.huseynov.security.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "my_users")
@Getter
@Setter
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String password;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    Set<Role> roles;
}
