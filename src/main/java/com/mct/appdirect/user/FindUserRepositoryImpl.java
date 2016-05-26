package com.mct.appdirect.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FindUserRepositoryImpl implements FindUserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userMapper = (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("email"), rs.getBoolean("cancelled"), rs.getString("first_name"), rs.getString("last_name"));

    @Autowired
    public FindUserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getUsers() {
        return jdbcTemplate.query("SELECT id, email, first_name, last_name, cancelled FROM user", userMapper);
    }
}
