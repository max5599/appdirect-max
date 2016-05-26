package com.mct.appdirect.user;

import com.mct.appdirect.utils.IntegrationTest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.OK;

@Sql({"classpath:db/user/cleanTables.sql","classpath:db/user/insertUser1.sql"})
public class ListUsersTest extends IntegrationTest {

    @Test
    public void shouldListUsers() throws Exception {
        ResponseEntity<String> response = callUsers();

        assertThat(response.getStatusCode(), equalTo(OK));
        assertThat(response.getBody(), equalTo("User 1 with email max@ence.com is Maxence Cramet and he or she is active"));
    }

    private ResponseEntity<String> callUsers() throws Exception {
        return unsecuredGet("/users", String.class);
    }
}
