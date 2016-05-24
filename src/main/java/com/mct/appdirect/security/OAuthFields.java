package com.mct.appdirect.security;

import java.util.Objects;

class OAuthFields {

    private final String consumerKey;
    private final String signature;

    OAuthFields(String consumerKey, String signature) {
        this.consumerKey = consumerKey;
        this.signature = signature;
    }

    String getConsumerKey() {
        return consumerKey;
    }

    String getSignature() {
        return signature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OAuthFields)) return false;
        OAuthFields that = (OAuthFields) o;
        return Objects.equals(consumerKey, that.consumerKey) &&
                Objects.equals(signature, that.signature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consumerKey, signature);
    }

    @Override
    public String toString() {
        return "OAuthFields{" +
                "consumerKey='" + consumerKey + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
