package com.mct.appdirect.subscription.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Creator {
    private final String email;
    private final String firstName;
    private final String lastName;

    @JsonCreator
    Creator(@JsonProperty(value = "email", required = true) String email,
            @JsonProperty(value = "firstName") String firstName,
            @JsonProperty(value = "lastName") String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Creator creator = (Creator) o;
        return Objects.equals(email, creator.email) &&
                Objects.equals(firstName, creator.firstName) &&
                Objects.equals(lastName, creator.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Creator{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
