package com.mct.appdirect.error;

public class ErrorResponseBuilder {

    private ErrorResponseBuilder() {}

    public static ErrorResponse aFailureResponseWithErrorCode(String errorCode) {
        ErrorResponse error = new ErrorResponse();
        error.setSuccess(false);
        error.setErrorCode(errorCode);
        return error;
    }

    static ErrorResponse internalErrorResponse() {
        return aFailureResponseWithErrorCode("INTERNAL_ERROR");
    }


}
