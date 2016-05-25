package com.mct.appdirect.subscription.security;

import com.mct.appdirect.utils.IntegrationTest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class SecurityTest extends IntegrationTest {

    @Test
    public void shouldReturnUnauthorizedIfNoSecurityIsProvidedInTheRequest() throws Exception {
        ResponseEntity<String> response = unsecuredGet("/subscription/create", String.class);

        assertThat(response.getStatusCode(), equalTo(UNAUTHORIZED));
    }
}
