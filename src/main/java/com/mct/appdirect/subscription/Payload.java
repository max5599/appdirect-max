package com.mct.appdirect.subscription;

import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class Payload {

    private Account account;

    public void setAccount(Account account) {
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
