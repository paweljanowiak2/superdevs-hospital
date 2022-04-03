package com.superdevs.hospitalmigration.config;

import com.superdevs.hospitalmigration.security.StaffValidatingFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    private final StaffValidatingFilter staffValidatingFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // here CSRF should be discussed
        http.csrf().disable()
                .addFilterAt(staffValidatingFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests().anyRequest().permitAll();
        // permitAll is done because of all security is done by filter
    }
}