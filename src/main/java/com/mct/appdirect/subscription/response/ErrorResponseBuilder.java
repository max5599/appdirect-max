package com.mct.appdirect.subscription.response;

public class ErrorResponseBuilder {

    public final static String USER_ALREADY_EXISTS = "USER_ALREADY_EXISTS";
    public final static String USER_NOT_FOUND = "USER_NOT_FOUND";
    public static final String ACCOUNT_NOT_FOUND = "ACCOUNT_NOT_FOUND";
    private static final String UNKNOWN_ERROR = "UNKNOWN_ERROR";
    private static final String INVALID_RESPONSE = "INVALID_RESPONSE";
    private static final String TRANSPORT_ERROR = "TRANSPORT_ERROR";

    private ErrorResponseBuilder() {}

    public static ErrorResponse internalErrorResponse() {
        return aFailureResponseWithErrorCode(UNKNOWN_ERROR);
    }

    public static ErrorResponse invalidResponse() {
        return aFailureResponseWithErrorCode(INVALID_RESPONSE);
    }

    public static ErrorResponse transportErrorResponse() {
        return aFailureResponseWithErrorCode(TRANSPORT_ERROR);
    }

    public static ErrorResponse aFailureResponseWithErrorCode(String errorCode) {
        ErrorResponse error = new ErrorResponse(false);
        error.setErrorCode(errorCode);
        return error;
    }


}
