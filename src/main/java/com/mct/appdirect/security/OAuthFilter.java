package com.mct.appdirect.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

class OAuthFilter extends OncePerRequestFilter {

    private final OAuthFieldsParser oAuthFieldsParser;
    private final String securityConsumerKey;

    OAuthFilter(OAuthFieldsParser oAuthFieldsParser, String securityConsumerKey) {
        this.oAuthFieldsParser = oAuthFieldsParser;
        this.securityConsumerKey = securityConsumerKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<OAuthFields> maybeOAuthFields = ofNullable(request.getHeader(AUTHORIZATION))
                .map(oAuthFieldsParser::parse)
                .orElseThrow(() -> new AccessDeniedException("No authorization header"));

        OAuthFields oAuthFields = maybeOAuthFields.orElseThrow(() -> new AccessDeniedException("Oauth fields are invalids"));
        if (!securityConsumerKey.equals(oAuthFields.getConsumerKey())) {
            throw new AccessDeniedException("Oauth consumer key is invalid");
        }

        filterChain.doFilter(request, response);
    }
}
