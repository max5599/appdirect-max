package com.mct.appdirect.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class BaseResponse {

    private final boolean success;

    @JsonCreator
    public BaseResponse(@JsonProperty(value = "success", required = true) boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseResponse)) return false;
        BaseResponse that = (BaseResponse) o;
        return success == that.success;
    }

    @Override
    public int hashCode() {
        return Objects.hash(success);
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "success=" + success +
                '}';
    }
}
