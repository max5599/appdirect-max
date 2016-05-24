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
    public ExpectedException thrown = ExpectedException.none();

    private final MockHttpServletRequest request = new MockHttpServletRequest("GET", "https://www.appdirect.com/api/billing/v1/orders");
    private final HttpServletResponse response = new MockHttpServletResponse();
    private final FilterChain filterChain = mock(FilterChain.class);

    private final OAuthFilter oAuthFilter = new OAuthFilter("Dummy");

    @Test
    public void shouldChainFilterIfRequestIsOk() throws Exception {
        request.addHeader(AUTHORIZATION, "oauth_consumer_key=Dummy");

        oAuthFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void shouldThrowAnAccessDeniedExceptionIfAuthorizationHeaderIsAbsent() throws Exception {
        thrown.expect(AccessDeniedException.class);
        thrown.expectMessage("No authorization header");

        oAuthFilter.doFilterInternal(request, response, filterChain);
    }

    @Test
    public void shouldThrowAnAccessDeniedExceptionIfConsumerKeyIsAbsent() throws Exception {
        thrown.expect(AccessDeniedException.class);
        thrown.expectMessage("Oauth consumer key is invalid");

        request.addHeader(AUTHORIZATION, "a_key=Dummy");

        oAuthFilter.doFilterInternal(request, response, filterChain);
    }

    @Test
    public void shouldThrowAnAccessDeniedExceptionIfConsumerKeyIsNotTheGoodOne() throws Exception {
        thrown.expect(AccessDeniedException.class);
        thrown.expectMessage("Oauth consumer key is invalid");

        request.addHeader(AUTHORIZATION, "oauth_consumer_key=NotAGoodOne");

        oAuthFilter.doFilterInternal(request, response, filterChain);
    }
}