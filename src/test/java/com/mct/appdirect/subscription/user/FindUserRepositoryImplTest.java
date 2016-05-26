package com.mct.appdirect.subscription.user;

import com.mct.appdirect.utils.RepositoryTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static com.mct.appdirect.subscription.user.UserBuilder.aUser;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

@Sql({"classpath:db/user/cleanTables.sql","classpath:db/user/insertUser1.sql"})
public class FindUserRepositoryImplTest extends RepositoryTest {

    @Autowired
    private FindUserRepository repository;

    @Test
    public void shouldListAllUsers() {
        List<User> users = repository.getUsers();

        User maxence = aUser().withId(1).withEmail("max@ence.com").active().withFirstName("Maxence").withLastName("Cramet").build();
        assertThat(users, contains(maxence));
    }
}