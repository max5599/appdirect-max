package com.mct.appdirect.security;

import java.util.Objects;

class OAuthFields {

    private final String consumerKey;

    OAuthFields(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OAuthFields)) return false;
        OAuthFields that = (OAuthFields) o;
        return Objects.equals(consumerKey, that.consumerKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consumerKey);
    }

    @Override
    public String toString() {
        return "OAuthFields{" +
                "consumerKey='" + consumerKey + '\'' +
                '}';
    }
}
