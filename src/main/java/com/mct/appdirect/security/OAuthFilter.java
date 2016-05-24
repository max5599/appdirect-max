package com.mct.appdirect.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class OAuthFilter extends OncePerRequestFilter {

    private static final String CONSUMER_KEY = "oauth_consumer_key";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> consumerKey = Optional.ofNullable(request.getHeader(CONSUMER_KEY));

        if (!consumerKey.isPresent()) {
            throw new AccessDeniedException("No oauth consumer key provided");
        }

        filterChain.doFilter(request, response);
    }
}
