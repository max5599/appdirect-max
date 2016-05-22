package com.mct.appdirect.error;

class ErrorResponseBuilder {

    private ErrorResponseBuilder() {}

    static ErrorResponse internalErrorResponse() {
        ErrorResponse error = new ErrorResponse();
        error.setSuccess(false);
        error.setErrorCode("INTERNAL_ERROR");
        return error;
    }
}
