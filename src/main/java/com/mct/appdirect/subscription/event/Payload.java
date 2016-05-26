package com.mct.appdirect.subscription.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class Payload {

    private final Account account;

    @JsonCreator
    public Payload(@JsonProperty(value = "accountIdentifier") Account account) {
        this.account = account;
    }

    public Optional<Account> getAccount() {
        return ofNullable(account);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payload)) return false;
        Payload payload = (Payload) o;
        return Objects.equals(account, payload.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account);
    }

    @Override
    public String toString() {
        return "Payload{" +
                "account=" + account +
                '}';
    }
}
