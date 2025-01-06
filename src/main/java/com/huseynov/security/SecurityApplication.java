package com.huseynov.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SecurityApplication {
    @Value("${SPRING_DATASOURCE_URL}")
    static String url;
    @Value("${secrets.db.username}")
    static String username;
    @Value("${secrets.db.password}")
    static String password;

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            System.out.println("url = " + url);
//            System.out.println("username = " + username);
//            System.out.println("password = " + password);
        };
    }

}
