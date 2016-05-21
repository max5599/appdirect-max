package com.mct.appdirect.subscription.create;

import org.junit.Test;

import static com.mct.appdirect.subscription.create.EventBuilder.anEvent;
import static com.mct.appdirect.subscription.create.UserCreationResult.userCreationSucceedWithId;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class CreateUserRepositoryImplTest {

    private final CreateUserRepositoryImpl repository = new CreateUserRepositoryImpl();

    @Test
    public void shouldCreateUserAndReturnItsGeneratedId() {
        UserCreationResult result = repository.createUser(anEvent().build());

        assertThat(result, equalTo(userCreationSucceedWithId("1")));
    }
}