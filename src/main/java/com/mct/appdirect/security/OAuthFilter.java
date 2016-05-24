package com.mct.appdirect.security;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.http.HttpRequest;
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

    private final String consumerKey;
    private final String secret;

    OAuthFilter(String consumerKey, String secret) {
        this.consumerKey = consumerKey;
        this.secret = secret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = ofNullable(request.getHeader(AUTHORIZATION))
                .orElseThrow(() -> new AccessDeniedException("No authorization header"));

        if(authorizationIsInvalid(request, authorization))
            throw new AccessDeniedException("Oauth signature is invalid");

        filterChain.doFilter(request, response);
    }

    private boolean authorizationIsInvalid(HttpServletRequest request, String authorization) {
        try {
            HttpRequest httpRequest = new HttpServletRequestAdapter(request);
            OAuthConsumer consumer = new DefaultOAuthConsumer(consumerKey, secret);
            consumer.sign(httpRequest);

            return !authorization.equals(httpRequest.getHeader(AUTHORIZATION));
        } catch (Exception e) {
            return true;
        }
    }
}
