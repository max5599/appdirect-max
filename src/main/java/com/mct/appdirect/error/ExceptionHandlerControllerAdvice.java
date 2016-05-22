package com.mct.appdirect.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mct.appdirect.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.mct.appdirect.response.ErrorResponseBuilder.internalErrorResponse;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ControllerAdvice
class ExceptionHandlerControllerAdvice {

    private final ErrorLogger errorLogger;

    @Autowired
    ExceptionHandlerControllerAdvice(ErrorLogger errorLogger) {
        this.errorLogger = errorLogger;
    }

    @ExceptionHandler(RuntimeException.class)
    public void handleInternalError(RuntimeException ex, HttpServletResponse response) throws IOException {
        errorLogger.logException(ex);

        response.setStatus(OK.value());
        response.setContentType(APPLICATION_JSON_VALUE);

        writeErrorToResponse(response);
    }

    private void writeErrorToResponse(HttpServletResponse response) throws IOException {
        ErrorResponse error = internalErrorResponse();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue( response.getWriter(), error);
    }
}
