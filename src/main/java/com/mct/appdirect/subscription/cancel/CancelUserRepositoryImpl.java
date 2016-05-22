package com.mct.appdirect.subscription.cancel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.mct.appdirect.response.ErrorResponseBuilder.USER_NOT_FOUND;
import static com.mct.appdirect.utils.StringUtils.isNumeric;
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
    public Optional<String> cancelUserAndReturnErrorIfPresent(String accountIdentifier) {
        return isNumeric(accountIdentifier).map(this::cancelUserWithId)
                .orElseGet(() -> of(USER_NOT_FOUND));
    }

    private Optional<String> cancelUserWithId(int accountIdentifier) {
        jdbcTemplate.update("UPDATE user SET cancelled=1 WHERE id=?", accountIdentifier);
        return empty();
    }
}
