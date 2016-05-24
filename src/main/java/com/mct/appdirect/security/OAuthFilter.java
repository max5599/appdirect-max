package com.mct.appdirect.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Optional.ofNullable;

public class OAuthFilter extends OncePerRequestFilter {

    private static final String CONSUMER_KEY = "oauth_consumer_key";

    private final String securityConsumerKey;

    public OAuthFilter(String securityConsumerKey) {
        this.securityConsumerKey = securityConsumerKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String consumerKey = ofNullable(request.getHeader(CONSUMER_KEY))
                .orElseThrow(() -> new AccessDeniedException("No oauth consumer key provided"));

        if (!consumerKey.equals(securityConsumerKey)) {
            throw new AccessDeniedException("Oauth consumer key is invalid");
        }

        filterChain.doFilter(request, response);
    }
}
