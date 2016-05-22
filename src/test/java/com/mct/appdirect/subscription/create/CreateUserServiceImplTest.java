package com.mct.appdirect.subscription.create;

import com.mct.appdirect.response.BaseResponse;
import com.mct.appdirect.response.ErrorResponse;
import org.junit.Test;

import static com.mct.appdirect.response.ErrorResponseBuilder.aFailureResponseWithErrorCode;
import static com.mct.appdirect.subscription.create.CreateResponseBuilder.aSuccessfulResponseWithAccountIdentifier;
import static com.mct.appdirect.subscription.create.UserCreationResult.userCreationFailedWithError;
import static com.mct.appdirect.subscription.create.UserCreationResult.userCreationSucceedWithId;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class CreateUserServiceImplTest {

    @Test
    public void shouldReturnASuccessfulResponseWithAccountIdentifierIfCreationSucceed() {
        CreateUserServiceImpl createUserService = createUserService(event -> userCreationSucceedWithId("123abc"));

        BaseResponse response = createUserService.createUserWithEventURL("eventURL");

        CreateResponse expectedResponse = aSuccessfulResponseWithAccountIdentifier("123abc");
        assertThat(response, equalTo(expectedResponse));
    }

    @Test
    public void shouldReturnAFailureWithErrorCodeIfCreationFailed() {
        CreateUserServiceImpl createUserService = createUserService(event -> userCreationFailedWithError("USER_ALREADY_EXISTS"));

        BaseResponse response = createUserService.createUserWithEventURL("eventURL");

        ErrorResponse expectedResponse = aFailureResponseWithErrorCode("USER_ALREADY_EXISTS");
        assertThat(response, equalTo(expectedResponse));
    }

    private CreateUserServiceImpl createUserService(CreateUserRepository createUserRepository) {
        return new CreateUserServiceImpl(eventUrl -> new Event(), createUserRepository);
    }
}