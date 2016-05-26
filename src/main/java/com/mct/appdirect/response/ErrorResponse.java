package com.mct.appdirect.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ErrorResponse extends BaseResponse {

    private String errorCode;

    @JsonCreator
    public ErrorResponse(@JsonProperty(value = "success", required = true) boolean success) {
        super(success);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorResponse)) return false;
        ErrorResponse that = (ErrorResponse) o;
        return Objects.equals(errorCode, that.errorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorCode);
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "errorCode='" + errorCode + '\'' +
                '}';
    }
}
