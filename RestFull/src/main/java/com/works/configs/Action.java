package com.works.configs;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
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


    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(Arrays.asList(
              new ConcurrentMapCache("customerList")
        ));
        return manager;
    }

}
