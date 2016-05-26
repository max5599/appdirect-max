package com.mct.appdirect.subscription.create;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mct.appdirect.response.BaseResponse;

import java.util.Objects;

class CreateResponse extends BaseResponse {

    private String accountIdentifier;

    @JsonCreator
    CreateResponse(@JsonProperty(value = "success", required = true) boolean success) {
        super(success);
    }

    void setAccountIdentifier(String accountIdentifier) {
        this.accountIdentifier = accountIdentifier;
    }

    public String getAccountIdentifier() {
        return accountIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateResponse)) return false;
        if (!super.equals(o)) return false;
        CreateResponse that = (CreateResponse) o;
        return Objects.equals(accountIdentifier, that.accountIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accountIdentifier);
    }

    @Override
    public String toString() {
        return "CreateResponse{" +
                "accountIdentifier='" + accountIdentifier + '\'' +
                '}';
    }
}
