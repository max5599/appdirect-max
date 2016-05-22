package com.mct.appdirect.subscription.cancel;

import com.mct.appdirect.subscription.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static java.util.Optional.empty;

@Repository
class CancelUserRepositoryImpl implements CancelUserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CancelUserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<String> cancelUserAndReturnErrorIfPresent(Event event) {
        jdbcTemplate.update("UPDATE user SET cancelled=1 WHERE id=?", event.getPayload().getAccount().getAccountIdentifier());

        return empty();
    }
}
