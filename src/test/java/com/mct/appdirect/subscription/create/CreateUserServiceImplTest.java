package com.mct.appdirect.subscription.create;

import org.junit.Test;

import static com.mct.appdirect.subscription.create.CreateResponseBuilder.aFailureResponseWithErrorCode;
import static com.mct.appdirect.subscription.create.CreateResponseBuilder.aSuccessfulResponseWithAccountIdentifier;
import static com.mct.appdirect.subscription.create.UserCreationResult.userCreationFailedWithError;
import static com.mct.appdirect.subscription.create.UserCreationResult.userCreationSuccedWithId;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class CreateUserServiceImplTest {

    @Test
    public void shouldReturnASuccessfulResponseWithAccountIdentifierIfCreationSucceed() {
        CreateUserServiceImpl createUserService = createUserService(event -> userCreationSuccedWithId("123abc"));

        CreateResponse createResponse = createUserService.createUserWithEventURL("eventURL");

        CreateResponse expectedResponse = aSuccessfulResponseWithAccountIdentifier("123abc");
        assertThat(createResponse, equalTo(expectedResponse));
    }

    @Test
    public void shouldReturnAFailureWithErrorCodeIfCreationFailed() {
        CreateUserServiceImpl createUserService = createUserService(event -> userCreationFailedWithError("USER_ALREADY_EXISTS"));

        CreateResponse createResponse = createUserService.createUserWithEventURL("eventURL");

        CreateResponse expectedResponse = aFailureResponseWithErrorCode("USER_ALREADY_EXISTS");
        assertThat(createResponse, equalTo(expectedResponse));
    }

    private CreateUserServiceImpl createUserService(CreateUserRepository createUserRepository) {
        return new CreateUserServiceImpl(eventUrl -> new Event(), createUserRepository);
    }
}