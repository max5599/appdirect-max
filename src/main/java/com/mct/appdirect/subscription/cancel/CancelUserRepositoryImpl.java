package com.mct.appdirect.subscription.cancel;

import com.mct.appdirect.subscription.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.mct.appdirect.response.ErrorResponseBuilder.ACCOUNT_NOT_FOUND;
import static java.util.Optional.empty;
import static java.util.Optional.of;

@Repository
class CancelUserRepositoryImpl implements CancelUserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CancelUserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<String> cancelUserAndReturnErrorIfPresent(Event event) {
        return event.getPayload().getAccount().map(account -> cancelUserWithId(account.getAccountIdentifier()))
                .orElseGet(() -> of(ACCOUNT_NOT_FOUND));

    }

    private Optional<String> cancelUserWithId(String accountIdentifier) {
        jdbcTemplate.update("UPDATE user SET cancelled=1 WHERE id=?", accountIdentifier);
        return empty();
    }
}
