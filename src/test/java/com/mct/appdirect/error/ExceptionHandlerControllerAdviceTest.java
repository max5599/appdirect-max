package com.mct.appdirect.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mct.appdirect.response.ErrorResponse;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import static com.mct.appdirect.response.ErrorResponseBuilder.internalErrorResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class ExceptionHandlerControllerAdviceTest {

    private final ExceptionHandlerControllerAdvice exceptionHandler = new ExceptionHandlerControllerAdvice();

    @Test
    public void shouldMapInternalErrorToErrorResponse() throws Exception {
        MockHttpServletResponse mockResponse = new MockHttpServletResponse();

        exceptionHandler.handleInternalError(mockResponse);

        assertThat(mockResponse.getStatus(), equalTo(200));
        assertThat(mockResponse.getContentType(), equalTo(APPLICATION_JSON_VALUE));

        ErrorResponse error = readContent(mockResponse);
        assertThat(error, equalTo(internalErrorResponse()));
    }

    private ErrorResponse readContent(MockHttpServletResponse mockResponse) throws java.io.IOException {
        String contentAsString = mockResponse.getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(contentAsString, ErrorResponse.class);
    }
}
