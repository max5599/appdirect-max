package com.mct.appdirect.subscription.user;

import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.ofNullable;

class User {

    private final long id;
    private final String email;
    private final boolean cancelled;
    private final String firstName;
    private final String lastName;

    User(long id, String email, boolean cancelled, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.cancelled = cancelled;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    boolean isCancelled() {
        return cancelled;
    }

    Optional<String> getFirstName() {
        return ofNullable(firstName);
    }

    Optional<String> getLastName() {
        return ofNullable(lastName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                cancelled == user.cancelled &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, cancelled, firstName, lastName);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", cancelled=" + cancelled +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
