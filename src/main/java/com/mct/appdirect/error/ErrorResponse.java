package com.mct.appdirect.error;

import java.util.Objects;

class ErrorResponse {

    private boolean success;
    private String errorCode;

    public boolean isSuccess() {
        return success;
    }

    void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorResponse that = (ErrorResponse) o;
        return success == that.success &&
                Objects.equals(errorCode, that.errorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, errorCode);
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "success=" + success +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
