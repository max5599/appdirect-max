package com.mct.appdirect.response;

public class ErrorResponseBuilder {

    public final static String USER_ALREADY_EXISTS = "USER_ALREADY_EXISTS";
    private static final String INTERNAL_ERROR = "INTERNAL_ERROR";

    private ErrorResponseBuilder() {}

    public static ErrorResponse internalErrorResponse() {
        return aFailureResponseWithErrorCode(INTERNAL_ERROR);
    }

    public static ErrorResponse aFailureResponseWithErrorCode(String errorCode) {
        ErrorResponse error = new ErrorResponse();
        error.setSuccess(false);
        error.setErrorCode(errorCode);
        return error;
    }


}
