package com.mct.appdirect.security;


import java.util.Optional;

interface OAuthValidator {

    Optional<String> validateAuthorizationAndReturnErrorIfFailed(String authorizationHeader);
}
