package com.mct.appdirect.security;

import java.util.Optional;

interface OAuthFieldsParser {
    Optional<OAuthFields> parse(String authorizationHeader);
}
