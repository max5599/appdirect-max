package com.mct.appdirect.subscription.cancel;

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
    public Optional<String> cancelUserAndReturnErrorIfPresent(String accountIdentifier) {
        return cancelUserWithId(accountIdentifier);
    }

    private Optional<String> cancelUserWithId(String accountIdentifier) {
        jdbcTemplate.update("UPDATE user SET cancelled=1 WHERE id=?", accountIdentifier);
        return empty();
    }
}
