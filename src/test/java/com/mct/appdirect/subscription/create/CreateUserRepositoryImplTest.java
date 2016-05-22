package com.mct.appdirect.subscription.create;

import com.mct.appdirect.Application;
import com.mct.appdirect.subscription.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.mct.appdirect.subscription.EventBuilder.anEvent;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@SqlGroup({
        @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:db/user/cleanTables.sql")})
public class CreateUserRepositoryImplTest {

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