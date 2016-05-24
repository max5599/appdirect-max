package com.mct.appdirect.error;

import com.mct.appdirect.response.ErrorResponse;
import com.mct.appdirect.utils.IntegrationTest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static com.mct.appdirect.response.ErrorResponseBuilder.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

public class ExceptionHandlerTest extends IntegrationTest {

    @Test
    public void shouldMapInternalErrorToAnErrorResponseWithStatusOk() {
        ResponseEntity<ErrorResponse> response = unsecuredGet("/exception/internal-error", ErrorResponse.class);

        assertThat(response.getStatusCode(), equalTo(OK));
        assertThat(response.getBody(), equalTo(internalErrorResponse()));
    }

    @Test
    public void shouldMapInvalidEventToAnErrorResponseWithStatusOk() {
        ResponseEntity<ErrorResponse> response = unsecuredGet("/exception/invalid-event", ErrorResponse.class);

        assertThat(response.getStatusCode(), equalTo(OK));
        assertThat(response.getBody(), equalTo(invalidResponse()));
    }

    @Test
    public void shouldMapTransportErrorToAnErrorResponseWithStatusOk() {
        ResponseEntity<ErrorResponse> response = unsecuredGet("/exception/transport-error", ErrorResponse.class);

        assertThat(response.getStatusCode(), equalTo(OK));
        assertThat(response.getBody(), equalTo(transportErrorResponse()));
    }

    @Test
    public void shouldMapIllegalArgumentToABadRequest() {
        ResponseEntity<Void> response = unsecuredGet("/exception/illegal-argument", Void.class);

        assertThat(response.getStatusCode(), equalTo(BAD_REQUEST));
    }
}
