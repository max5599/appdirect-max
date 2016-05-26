package com.mct.appdirect.subscription.create;

import com.mct.appdirect.subscription.Event;
import com.mct.appdirect.subscription.user.FindUserRepository;
import com.mct.appdirect.subscription.user.User;
import com.mct.appdirect.utils.RepositoryTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static com.mct.appdirect.subscription.EventBuilder.anEvent;
import static com.mct.appdirect.subscription.user.UserBuilder.aUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Sql("classpath:db/user/cleanTables.sql")
public class CreateUserRepositoryImplTest extends RepositoryTest {

    @Autowired
    private FindUserRepository findUserRepository;

    @Autowired
    private CreateUserRepositoryImpl createRepository;

    @Test
    public void shouldCreateUserAndReturnItsGeneratedId() {
        Event event = anEvent()
                .withEmail("max@ence.com")
                .withFirstName("Maxence")
                .withLastName("Cramet")
                .build();
        UserCreationResult result = createRepository.createUser(event);

        long generatedId = result.map(Long::valueOf, error -> -1L);
        assertThat(generatedId, greaterThan(0L));

        User expectedUser = aUser().withId(generatedId)
                .withEmail("max@ence.com")
                .withFirstName("Maxence")
                .withLastName("Cramet")
                .active()
                .build();
        assertThat(findUserRepository.getUsers(), hasItems(expectedUser));
    }

    @Test
    public void shouldReturnAFailureResponseWhenAUserWithSameEmailAlreadyExists() {
        Event firstEvent = anEvent().withEmail("max@ence.com").build();
        Event eventWithDuplicatedEmail = anEvent().withEmail("max@ence.com").build();

        createRepository.createUser(firstEvent);
        UserCreationResult result = createRepository.createUser(eventWithDuplicatedEmail);

        assertThat(result.map(id -> "id", error -> error), equalTo("USER_ALREADY_EXISTS"));
    }
}