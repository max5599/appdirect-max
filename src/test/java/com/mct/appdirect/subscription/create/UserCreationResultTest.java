package com.mct.appdirect.subscription.create;

import org.junit.Test;

import static com.mct.appdirect.subscription.create.UserCreationResult.userCreationFailedWithError;
import static com.mct.appdirect.subscription.create.UserCreationResult.userCreationSucceedWithId;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class UserCreationResultTest {

    @Test
    public void shouldMapUserIdWhenIsSuccess() {
        UserCreationResult result = userCreationSucceedWithId("123");

        assertThat(result.map(s -> "SUCCESS", s -> "FAILURE"), equalTo("SUCCESS"));
    }

    @Test
    public void shouldMapErrorCodeWhenIsFailure() {
        UserCreationResult result = userCreationFailedWithError("ERROR");

        assertThat(result.map(s -> "SUCCESS", s -> "FAILURE"), equalTo("FAILURE"));
    }
}