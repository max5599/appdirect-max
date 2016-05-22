package com.mct.appdirect.subscription;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Account {
    private final String accountIdentifier;

    @JsonCreator
    public Account(@JsonProperty(value = "accountIdentifier", required = true) String accountIdentifier) {
        this.accountIdentifier = accountIdentifier;
    }

    public String getAccountIdentifier() {
        return accountIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(accountIdentifier, account.accountIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountIdentifier);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountIdentifier='" + accountIdentifier + '\'' +
                '}';
    }
}
