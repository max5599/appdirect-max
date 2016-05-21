package com.mct.appdirect.subscription.create;

import java.util.Objects;

class CreateResponse {

    private boolean success;
    private String accountIdentifier;
    private String errorCode;

    CreateResponse() {}

    void setSuccess(boolean success) {
        this.success = success;
    }

    void setAccountIdentifier(String accountIdentifier) {
        this.accountIdentifier = accountIdentifier;
    }

    void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getAccountIdentifier() {
        return accountIdentifier;
    }

    public String getErrorCode() {
        return errorCode;
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
