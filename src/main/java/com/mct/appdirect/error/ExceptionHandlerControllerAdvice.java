package com.mct.appdirect.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mct.appdirect.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.mct.appdirect.response.ErrorResponseBuilder.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ControllerAdvice
class ExceptionHandlerControllerAdvice {

    private final ErrorLogger errorLogger;

    @Autowired
    ExceptionHandlerControllerAdvice(ErrorLogger errorLogger) {
        this.errorLogger = errorLogger;
    }

    @ExceptionHandler(InvalidEventException.class)
    public void handleInvalidEventException(InvalidEventException ex, HttpServletResponse response) throws IOException {
        logErrorAndSetupResponse(invalidResponse(), ex, response);
    }

    @ExceptionHandler(TransportException.class)
    public void handleTransportException(TransportException ex, HttpServletResponse response) throws IOException {
        logErrorAndSetupResponse(transportErrorResponse(), ex, response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    public void handleIllegalArgumentException(IllegalArgumentException ex) {
        errorLogger.logException("BAD_REQUEST", ex);
    }

    @ExceptionHandler(RuntimeException.class)
    public void handleInternalError(RuntimeException ex, HttpServletResponse response) throws IOException {
        logErrorAndSetupResponse(internalErrorResponse(), ex, response);
    }

    private void logErrorAndSetupResponse(ErrorResponse error, Throwable ex, HttpServletResponse response) throws IOException {
        errorLogger.logException(error.getErrorCode(), ex);

        response.setStatus(OK.value());
        response.setContentType(APPLICATION_JSON_VALUE);

        writeErrorToResponse(response, error);
    }

    private void writeErrorToResponse(HttpServletResponse response, ErrorResponse error) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue( response.getWriter(), error);
    }
}
