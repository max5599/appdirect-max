package com.mct.appdirect.security;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

class OAuthFields {

    private static final String CONSUMER_KEY = "oauth_consumer_key";

    private final Map<String, String> fields;

    OAuthFields(String authorizationHeader) {
        fields = stream(authorizationHeader.split(","))
                .map(s -> s.split("="))
                .filter(s -> s.length == 2)
                .collect(toMap(s -> s[0], s -> s[1]));
    }

    String getConsumerKey() {
        return fields.get(CONSUMER_KEY);
    }
}
