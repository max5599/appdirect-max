package com.mct.appdirect.subscription.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ErrorResponse extends BaseResponse {

    private final String errorCode;

    @JsonCreator
    ErrorResponse(@JsonProperty(value = "success", required = true) boolean success,
                  @JsonProperty(value = "errorCode", required = true) String errorCode) {
        super(success);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
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
