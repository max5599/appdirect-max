package com.mct.appdirect.security;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class OAuthFilterTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final HttpServletRequest aRequestWithAuthorization = aRequestWithAuthorization();
    private final HttpServletRequest aRequestWithoutAuthorization = new MockHttpServletRequest("GET", "https://www.appdirect.com/api/billing/v1/orders");
    private final HttpServletResponse response = new MockHttpServletResponse();
    private final FilterChain filterChain = mock(FilterChain.class);

    @Test
    public void shouldChainFilterIfRequestAuthorisationIsOk() throws Exception {
        OAuthFilter oAuthFilter = new OAuthFilter(h -> of(new OAuthFields("Dummy", "Lzlw8l5rbrN8jXZbuOFBakGubbg=")), "Dummy", "secret");

        oAuthFilter.doFilterInternal(aRequestWithAuthorization, response, filterChain);

        verify(filterChain).doFilter(aRequestWithAuthorization, response);
    }

    @Test
    public void shouldThrowAnAccessDeniedExceptionIfAuthorizationHeaderIsAbsent() throws Exception {
        OAuthFilter oAuthFilter = new OAuthFilter(h -> of(new OAuthFields("Dummy", "signature")), "Dummy", "secret");

        thrown.expect(AccessDeniedException.class);
        thrown.expectMessage("No authorization header");

        oAuthFilter.doFilterInternal(aRequestWithoutAuthorization, response, filterChain);
    }

    @Test
    public void shouldThrowAnAccessDeniedExceptionIfParserReturnsEmpty() throws Exception {
        OAuthFilter oAuthFilter = new OAuthFilter(h -> empty(), "Dummy", "secret");

        thrown.expect(AccessDeniedException.class);
        thrown.expectMessage("Oauth fields are invalids");

        oAuthFilter.doFilterInternal(aRequestWithAuthorization, response, filterChain);
    }

    @Test
    public void shouldThrowAnAccessDeniedExceptionIfConsumerKeyIsNotTheGoodOne() throws Exception {
        OAuthFilter oAuthFilter = new OAuthFilter(h -> of(new OAuthFields("NotAGoodOne", "signature")), "Dummy", "secret");

        thrown.expect(AccessDeniedException.class);
        thrown.expectMessage("Oauth consumer key is invalid");

        oAuthFilter.doFilterInternal(aRequestWithAuthorization, response, filterChain);
    }

    @Test
    public void shouldThrowAnAccessDeniedExceptionIfSignatureIsInvalid() throws Exception {
        OAuthFilter oAuthFilter = new OAuthFilter(h -> of(new OAuthFields("Dummy", "invalidSignature")), "Dummy", "secret");

        thrown.expect(AccessDeniedException.class);
        thrown.expectMessage("Oauth signature is invalid");

        oAuthFilter.doFilterInternal(aRequestWithAuthorization, response, filterChain);
    }

    private HttpServletRequest aRequestWithAuthorization() {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "https://www.appdirect.com/api/billing/v1/orders");
        request.addHeader(AUTHORIZATION, "oauth...");
        return request;
    }

}