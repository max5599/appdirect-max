package com.mct.appdirect.subscription.create;

import com.mct.appdirect.subscription.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

import static com.mct.appdirect.subscription.create.UserCreationResult.userCreationFailedWithError;
import static com.mct.appdirect.subscription.create.UserCreationResult.userCreationSucceedWithId;
import static com.mct.appdirect.subscription.response.ErrorResponseBuilder.USER_ALREADY_EXISTS;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Repository
class CreateUserRepositoryImpl implements CreateUserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    CreateUserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserCreationResult createUser(Event event) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement("INSERT INTO user(email, first_name, last_name) VALUES (?, ?, ?)", RETURN_GENERATED_KEYS);
                ps.setString(1, event.getCreator().getEmail());
                ps.setString(2, event.getCreator().getFirstName());
                ps.setString(3, event.getCreator().getLastName());
                return ps;
            }, keyHolder);

            return userCreationSucceedWithId(keyHolder.getKey().toString());
        } catch (DuplicateKeyException e) {
            return userCreationFailedWithError(USER_ALREADY_EXISTS);
        }
    }
}
