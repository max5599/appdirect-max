package com.mct.appdirect.security;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

@Service
class OAuthFieldsParserImpl implements OAuthFieldsParser {

    private static final String OAUTH_CONSUMER_KEY = "oauth_consumer_key";

    private static final Set<String> REQUIRED_FIELDS = Stream.of(
            "OAuth realm",
            "oauth_nonce",
            "oauth_timestamp",
            OAUTH_CONSUMER_KEY,
            "oauth_signature_method",
            "oauth_version",
            "oauth_signature").collect(toSet());

    @Override
    public Optional<OAuthFields> parse(String authorizationHeader) {
        Map<String, String> fields = stream(authorizationHeader.split(","))
                .map(s -> s.split("=\""))
                .filter(s -> s.length == 2)
                .collect(toMap(s -> s[0], s -> s[1].replace("\"","")));

        if(REQUIRED_FIELDS.stream().allMatch(fields::containsKey)) {
            return of(new OAuthFields(fields.get(OAUTH_CONSUMER_KEY)));
        } else {
            return empty();
        }
    }
}
