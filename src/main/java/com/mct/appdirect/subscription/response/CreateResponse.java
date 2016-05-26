package com.mct.appdirect.subscription.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CreateResponse extends BaseResponse {

    private final String accountIdentifier;

    @JsonCreator
    CreateResponse(@JsonProperty(value = "success", required = true) boolean success,
                   @JsonProperty(value = "accountIdentifier", required = true) String accountIdentifier) {
        super(success);
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
