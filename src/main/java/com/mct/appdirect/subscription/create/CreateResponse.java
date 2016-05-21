package com.mct.appdirect.subscription.create;

import java.util.Objects;
import java.util.Optional;

class CreateResponse {

    private final boolean success;
    private final String accountIdentifier;
    private final String errorCode;

    CreateResponse(boolean success, String accountIdentifier, String errorCode) {
        this.success = success;
        this.accountIdentifier = accountIdentifier;
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public Optional<String> getAccountIdentifier() {
        return Optional.ofNullable(accountIdentifier);
    }

    public Optional<String> getErrorCode() {
        return Optional.ofNullable(errorCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateResponse that = (CreateResponse) o;
        return success == that.success &&
                Objects.equals(accountIdentifier, that.accountIdentifier) &&
                Objects.equals(errorCode, that.errorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, accountIdentifier, errorCode);
    }

    @Override
    public String toString() {
        return "CreateResponse{" +
                "success=" + success +
                ", accountIdentifier='" + accountIdentifier + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
