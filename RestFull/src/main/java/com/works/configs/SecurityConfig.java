package com.works.configs;

import com.works.services.AdminDetailService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final PasswordEncoder encoder;
    final AdminDetailService detailService;
    public SecurityConfig(PasswordEncoder encoder, AdminDetailService detailService) {
        this.encoder = encoder;
        this.detailService = detailService;
    }

    // sql query -> admin and role control
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailService).passwordEncoder(encoder);
    }

    // role -> rest controller router
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
        .httpBasic()
        .and()
        .authorizeRequests()

        .antMatchers("/product/**").hasRole("product")
        .antMatchers("/note/**").hasRole("note")
        .antMatchers("/customer/**").hasRole("customer")
        .and().csrf().disable().formLogin().disable();

        //http.csrf().disable().formLogin().disable(); // Security config disable
        http.headers().frameOptions().disable(); // h2-console vb security disables
    }

}
