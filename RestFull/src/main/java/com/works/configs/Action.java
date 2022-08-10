package com.works.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;

@Configuration
public class Action {

    @Bean(name = "rnd1")
    public Random rnd() {
        System.out.println("Random Call-1");
        return new Random();
    }

    @Bean(name = "rnd2")
    public Random rnd1() {
        System.out.println("Random Call-2");
        return new Random();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


}
