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

import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

class OAuthFilter extends OncePerRequestFilter {

    private final OAuthConsumer consumer;

    OAuthFilter(String consumerKey, String secret) {
        consumer = new DefaultOAuthConsumer(consumerKey, secret);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authorization = ofNullable(request.getHeader(AUTHORIZATION))
                    .orElseThrow(() -> new AccessDeniedException("No authorization header"));

            if(authorizationIsInvalid(request, authorization))
                throw new AccessDeniedException(format("Oauth signature '%s' is invalid", authorization));

            filterChain.doFilter(request, response);
        } catch(AccessDeniedException e) {
            response.sendError(UNAUTHORIZED.value());
            throw e;
        }
    }

    private boolean authorizationIsInvalid(HttpServletRequest request, String authorization) {
        try {
            HttpRequest httpRequest = new HttpServletRequestAdapter(request);
            consumer.sign(httpRequest);

            return !authorization.equals(httpRequest.getHeader(AUTHORIZATION));
        } catch (Exception e) {
            throw new AccessDeniedException("Impossible to sign the request", e);
        }
    }
}
