package com.mct.appdirect.subscription.create;

import com.mct.appdirect.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

import static com.mct.appdirect.subscription.create.EventBuilder.anEvent;
import static java.util.Optional.empty;
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

        assertThat(result.getUserId(), not(empty()));
        assertThat(result.getErrorCode(), is(empty()));
    }

    @Test
    public void shouldReturnAFailureResponseWhenAUserWithSameEmailAlreadyExists() {
        Event firstEvent = anEvent().withEmail("max@ence.com").build();
        Event eventWithDuplicatedEmail = anEvent().withEmail("max@ence.com").build();

        repository.createUser(firstEvent);
        UserCreationResult result = repository.createUser(eventWithDuplicatedEmail);

        assertThat(result.getUserId(), is(empty()));
        assertThat(result.getErrorCode(), equalTo(Optional.of("USER_ALREADY_EXISTS")));
    }
}