package com.dogukanyildirim.airlinesticketingsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/airline-company/**").permitAll()
//                .antMatchers("/airport/**").permitAll()
//                .antMatchers("/airplane/**").permitAll()
//                .antMatchers("/route/**").permitAll()
//                .antMatchers("/flight/**").permitAll()
//                .antMatchers("/login/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .httpBasic()
                .realmName("Airlines Ticketing System");
    }
}