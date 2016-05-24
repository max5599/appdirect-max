package com.mct.appdirect.security;

import org.junit.Test;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class OAuthValidatorImplTest {



    @Test
    public void shouldReturnOauthFieldsAreInvalidsIfAuthorizationIsNotValid() throws Exception {
        OAuthValidatorImpl authValidator = new OAuthValidatorImpl(authorizationHeader -> empty());

        Optional<String> error = authValidator.validateAuthorizationAndReturnErrorIfFailed("Invalid authorization");

        assertThat(error, equalTo(of("Oauth fields are invalids")))  ;
    }
}