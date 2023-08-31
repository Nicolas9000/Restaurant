package com.example.api.config;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Bean
    public PasswordEncoder passwordEncorder() {

        return new BCryptPasswordEncoder();
    }

    public Boolean passwordVerify(String password, String dbPassword) {
        return BCrypt.checkpw(password, dbPassword);
    }
}
