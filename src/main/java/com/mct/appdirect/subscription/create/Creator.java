package com.mct.appdirect.subscription.create;

import java.util.Objects;

class Creator {
    private String email;
    private String firstName;
    private String lastName;

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
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
