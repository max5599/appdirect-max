package com.mct.appdirect.subscription.create;

import com.mct.appdirect.subscription.Event;
import com.mct.appdirect.utils.RepositoryTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.mct.appdirect.subscription.EventBuilder.anEvent;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class CreateUserRepositoryImplTest extends RepositoryTest {

    @Autowired
    private CreateUserRepositoryImpl repository;

    @Test
    public void shouldCreateUserAndReturnItsGeneratedId() {
        Event event = anEvent()
                .withEmail("max@ence.com")
                .withFirstName("Maxence")
                .withLastName("Cramet")
                .build();
        UserCreationResult result = repository.createUser(event);

        assertThat(result.map(id -> id, error -> error), not(isEmptyOrNullString()));
    }

    @Test
    public void shouldReturnAFailureResponseWhenAUserWithSameEmailAlreadyExists() {
        Event firstEvent = anEvent().withEmail("max@ence.com").build();
        Event eventWithDuplicatedEmail = anEvent().withEmail("max@ence.com").build();

        repository.createUser(firstEvent);
        UserCreationResult result = repository.createUser(eventWithDuplicatedEmail);

        assertThat(result.map(id -> id, error -> error), equalTo("USER_ALREADY_EXISTS"));
    }
}