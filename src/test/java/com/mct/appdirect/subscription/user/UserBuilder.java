package com.mct.appdirect.subscription.user;

public class UserBuilder {

    private long id = 1;
    private String email = "max@ence.com";
    private String firstName;
    private String lastName;
    private boolean cancelled = false;

    private UserBuilder() {};

    public static UserBuilder aUser() {
     return new UserBuilder();
    }

    public UserBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder active() {
        this.cancelled = false;
        return this;
    }

    public UserBuilder inactive() {
        this.cancelled = true;
        return this;
    }

    public User build() {
        return new User(id, email, cancelled, firstName, lastName);
    }
}
