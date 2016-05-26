package com.mct.appdirect.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mct.appdirect.subscription.response.ErrorResponse;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import static com.mct.appdirect.subscription.response.ErrorResponseBuilder.*;
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

        assertErrorInResponseAndErrorLogging(mockResponse, internalErrorResponse(), ex);
    }

    @Test
    public void shouldMapInvalidEventErrorToErrorResponseAndLogError() throws Exception {
        InvalidEventException ex = new InvalidEventException("Invalid event", new Exception("Parsing error"));
        MockHttpServletResponse mockResponse = new MockHttpServletResponse();

        exceptionHandler.handleInvalidEventException(ex, mockResponse);

        assertErrorInResponseAndErrorLogging(mockResponse, invalidResponse(), ex);
    }

    @Test
    public void shouldMapTransportErrorToErrorResponseAndLogError() throws Exception {
        TransportException ex = new TransportException("Transport exception", new Exception("Timeout"));
        MockHttpServletResponse mockResponse = new MockHttpServletResponse();

        exceptionHandler.handleTransportException(ex, mockResponse);

        assertErrorInResponseAndErrorLogging(mockResponse, transportErrorResponse(), ex);
    }

    @Test
    public void shouldLogErrorForIllegalArgument() {
        IllegalArgumentException ex = new IllegalArgumentException("Illegal Argument");

        exceptionHandler.handleIllegalArgumentException(ex);

        verify(errorLogger).logException("BAD_REQUEST", ex);
    }

    private void assertErrorInResponseAndErrorLogging(MockHttpServletResponse response, ErrorResponse expectedError, RuntimeException exceptionToHandle) throws java.io.IOException {
        assertThat(response.getStatus(), equalTo(200));
        assertThat(response.getContentType(), equalTo(APPLICATION_JSON_VALUE));

        ErrorResponse error = readContent(response);
        assertThat(error, equalTo(expectedError));

        verify(errorLogger).logException(expectedError.getErrorCode(), exceptionToHandle);
    }

    private ErrorResponse readContent(MockHttpServletResponse mockResponse) throws java.io.IOException {
        String contentAsString = mockResponse.getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(contentAsString, ErrorResponse.class);
    }
}
