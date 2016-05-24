package com.mct.appdirect.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${app.security.consumerKey}")
    private String consumerKey;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/subscription/**").addFilterBefore(new OAuthFilter(consumerKey), BasicAuthenticationFilter.class);
    }
}
