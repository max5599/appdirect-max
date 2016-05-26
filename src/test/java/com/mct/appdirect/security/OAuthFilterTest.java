package com.mct.appdirect.security;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class OAuthFilterTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private final MockHttpServletRequest aRequest = new MockHttpServletRequest("GET", "https://www.appdirect.com/api/billing/v1/orders");
    private final HttpServletResponse response = new MockHttpServletResponse();
    private final FilterChain filterChain = mock(FilterChain.class);
    private final OAuthFilter oAuthFilter= new OAuthFilter("Dummy", "secret");

    @Test
    public void shouldChainFilterIfRequestAuthorisationIsOk() throws Exception {
        aRequest.addHeader(AUTHORIZATION, "OAuth oauth_consumer_key=\"Dummy\", oauth_nonce=\"5159593733537615987\", oauth_signature=\"ncYIOGQzWwT8TVPwRhuSTsmmBDY%3D\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"1464129560\", oauth_version=\"1.0\"");

        oAuthFilter.doFilterInternal(aRequest, response, filterChain);

        verify(filterChain).doFilter(aRequest, response);
    }

    @Test
    public void shouldThrowAnAccessDeniedExceptionIfAuthorizationHeaderIsAbsent() throws Exception {
        thrown.expect(AccessDeniedException.class);
        thrown.expectMessage("No authorization header");

        oAuthFilter.doFilterInternal(aRequest, response, filterChain);
    }

    @Test
    public void shouldThrowAnAccessDeniedExceptionIfAuthorizationIsInvalid() throws Exception {
        aRequest.addHeader(AUTHORIZATION, "A bad author");

        thrown.expect(AccessDeniedException.class);
        thrown.expectMessage("Oauth signature 'A bad author' is invalid");

        oAuthFilter.doFilterInternal(aRequest, response, filterChain);
    }
}