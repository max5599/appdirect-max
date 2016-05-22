package com.mct.appdirect.subscription.cancel;

import com.mct.appdirect.utils.RepositoryTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static java.util.Optional.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;
@Sql(executionPhase = BEFORE_TEST_METHOD, scripts = {"classpath:db/user/cleanTables.sql","classpath:db/user/insertUser1.sql"})
public class CancelUserRepositoryImplTest extends RepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CancelUserRepository repository;

    @Test
    public void shouldCancelUserAndReturnNoErrorIfSucceed() {
        Optional<String> error = repository.cancelUserAndReturnErrorIfPresent("1");

        assertThat(error, is(empty()));

        assertThat(countRowsInTableWhere(jdbcTemplate, "user", "id=1 and cancelled"), equalTo(1));
    }
}