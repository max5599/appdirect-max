package com.mct.appdirect.security;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

@Service
class OAuthFieldsParserImpl implements OAuthFieldsParser {

    private static Pattern FIELDS_PATTERN = Pattern.compile("(oauth_.+)=\"(.*)\"");

    private static final String OAUTH_CONSUMER_KEY = "oauth_consumer_key";
    private static final String OAUTH_SIGNATURE = "oauth_signature";

    private static final Set<String> REQUIRED_FIELDS = Stream.of(
            "oauth_nonce",
            "oauth_timestamp",
            OAUTH_CONSUMER_KEY,
            "oauth_signature_method",
            "oauth_version",
            OAUTH_SIGNATURE).collect(toSet());

    @Override
    public Optional<OAuthFields> parse(String authorizationHeader) {

        Map<String, String> fields = stream(authorizationHeader.split(","))
                .map(this::extractWithRegex)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));

        if(REQUIRED_FIELDS.stream().allMatch(fields::containsKey)) {
            return of(new OAuthFields(fields.get(OAUTH_CONSUMER_KEY), fields.get(OAUTH_SIGNATURE)));
        } else {
            return empty();
        }
    }

    private Optional<Map.Entry<String, String>> extractWithRegex(String field) {
        Matcher m = FIELDS_PATTERN.matcher(field);
        if(m.find()) {
            return of(new HashMap.SimpleEntry<>(m.group(1), m.group(2)));
        } else {
            return empty();
        }
    }
}
