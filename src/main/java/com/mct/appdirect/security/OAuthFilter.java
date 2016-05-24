package com.mct.appdirect.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

class OAuthFilter extends OncePerRequestFilter {

    private final String securityConsumerKey;

    OAuthFilter(String securityConsumerKey) {
        this.securityConsumerKey = securityConsumerKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        OAuthFields oAuthFields = ofNullable(request.getHeader(AUTHORIZATION))
                .map(OAuthFields::new)
                .orElseThrow(() -> new AccessDeniedException("No authorization header"));

        if (!securityConsumerKey.equals(oAuthFields.getConsumerKey())) {
            throw new AccessDeniedException("Oauth consumer key is invalid");
        }

        filterChain.doFilter(request, response);
    }
}
