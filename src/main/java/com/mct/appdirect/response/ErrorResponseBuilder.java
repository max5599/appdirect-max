package com.mct.appdirect.response;

public class ErrorResponseBuilder {

    public final static String USER_ALREADY_EXISTS = "USER_ALREADY_EXISTS";
    private static final String UNKNOWN_ERROR = "UNKNOWN_ERROR";
    private static final String INVALID_RESPONSE = "INVALID_RESPONSE";

    private ErrorResponseBuilder() {}

    public static ErrorResponse internalErrorResponse() {
        return aFailureResponseWithErrorCode(UNKNOWN_ERROR);
    }

    public static ErrorResponse invalidResponse() {
        return aFailureResponseWithErrorCode(INVALID_RESPONSE);
    }

    public static ErrorResponse aFailureResponseWithErrorCode(String errorCode) {
        ErrorResponse error = new ErrorResponse();
        error.setSuccess(false);
        error.setErrorCode(errorCode);
        return error;
    }


}
