package com.mct.appdirect.security;

import java.util.Optional;


class OAuthValidatorImpl implements OAuthValidator {

    private final OAuthFieldsParser oAuthFieldsParser;

    OAuthValidatorImpl(OAuthFieldsParser oAuthFieldsParser) {
        this.oAuthFieldsParser = oAuthFieldsParser;
    }

    @Override
    public Optional<String> validateAuthorizationAndReturnErrorIfFailed(String authorizationHeader) {
        return oAuthFieldsParser.parse(authorizationHeader).map(oAuthFields -> Optional.<String>empty())
                .orElse(Optional.of("Oauth fields are invalids"));
    }
}
