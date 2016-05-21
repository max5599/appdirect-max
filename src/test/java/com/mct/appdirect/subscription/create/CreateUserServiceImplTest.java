package com.mct.appdirect.subscription.create;

import org.junit.Test;

import static com.mct.appdirect.subscription.create.CreateResponseBuilder.aCreateResponse;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class CreateUserServiceImplTest {

    private final NotificationEventRetriever eventRetriever = eventUrl -> new Event();
    private final CreateUserRepository createUserRepository = event -> "123abc";

    private final CreateUserServiceImpl createUserService = new CreateUserServiceImpl(eventRetriever, createUserRepository);

    @Test
    public void shouldReturnASuccessfulResponseWithAccountIdentifierIfCreationSucceed() {
        CreateResponse createResponse = createUserService.createUserWithEventURL("eventURL");

        CreateResponse expectedResponse = aCreateResponse().withSuccess().withAccountIdentifier("123abc").build();
        assertThat(createResponse, equalTo(expectedResponse));
    }
}