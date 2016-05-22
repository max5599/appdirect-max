package com.mct.appdirect.error;

import com.mct.appdirect.response.ErrorResponse;
import com.mct.appdirect.utils.IntegrationTest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static com.mct.appdirect.error.ErrorResponseBuilder.internalErrorResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.OK;

public class ExceptionHandlerTest extends IntegrationTest {

    @Test
    public void shouldMapInternalErrorToAnErrorResponseWithStatusOk() {
        ResponseEntity<ErrorResponse> response = template.getForEntity(getBaseUrl() + "/exception", ErrorResponse.class);

        assertThat(response.getStatusCode(), equalTo(OK));
        assertThat(response.getBody(), equalTo(internalErrorResponse()));
    }
}
