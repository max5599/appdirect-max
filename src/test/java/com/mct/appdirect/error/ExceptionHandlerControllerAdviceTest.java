package com.mct.appdirect.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mct.appdirect.response.ErrorResponse;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import static com.mct.appdirect.response.ErrorResponseBuilder.internalErrorResponse;
import static com.mct.appdirect.response.ErrorResponseBuilder.invalidResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class ExceptionHandlerControllerAdviceTest {

    private final ErrorLogger errorLogger = mock(ErrorLogger.class);

    private final ExceptionHandlerControllerAdvice exceptionHandler = new ExceptionHandlerControllerAdvice(errorLogger);

    @Test
    public void shouldMapInternalErrorToErrorResponseAndLogError() throws Exception {
        RuntimeException ex = new RuntimeException("ERROR!");
        MockHttpServletResponse mockResponse = new MockHttpServletResponse();

        exceptionHandler.handleInternalError(ex, mockResponse);

        assertThat(mockResponse.getStatus(), equalTo(200));
        assertThat(mockResponse.getContentType(), equalTo(APPLICATION_JSON_VALUE));

        ErrorResponse error = readContent(mockResponse);
        assertThat(error, equalTo(internalErrorResponse()));

        verify(errorLogger).logException("UNKNOWN_ERROR", ex);
    }

    @Test
    public void shouldMapInvalidEventErrorToErrorResponseAndLogError() throws Exception {
        InvalidEventException ex = new InvalidEventException("Invalid event", new Exception("Parsing error"));
        MockHttpServletResponse mockResponse = new MockHttpServletResponse();

        exceptionHandler.handleInvalidEventException(ex, mockResponse);

        assertThat(mockResponse.getStatus(), equalTo(200));
        assertThat(mockResponse.getContentType(), equalTo(APPLICATION_JSON_VALUE));

        ErrorResponse error = readContent(mockResponse);
        assertThat(error, equalTo(invalidResponse()));

        verify(errorLogger).logException("INVALID_RESPONSE", ex);
    }

    private ErrorResponse readContent(MockHttpServletResponse mockResponse) throws java.io.IOException {
        String contentAsString = mockResponse.getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(contentAsString, ErrorResponse.class);
    }
}
