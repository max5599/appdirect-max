package com.mct.appdirect.user;

import java.util.Optional;

class User {

    private final long id;
    private final String email;
    private final boolean active;
    private String firstName;
    private String lastName;

    User(long id, String email, boolean active) {
        this.id = id;
        this.email = email;
        this.active = active;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    boolean isActive() {
        return active;
    }

    Optional<String> getFirstName() {
        return Optional.ofNullable(firstName);
    }

    Optional<String> getLastName() {
        return Optional.ofNullable(lastName);
    }
}
