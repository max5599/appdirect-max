package com.mct.appdirect.user;

public class UserBuilder {

    private long id = 1;
    private String email = "max@ence.com";
    private String firstName;
    private String lastName;
    private boolean active = false;

    private UserBuilder() {};

    static UserBuilder aUser() {
     return new UserBuilder();
    }

    UserBuilder withId(long id) {
        this.id = id;
        return this;
    }

    UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    UserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    UserBuilder active() {
        this.active = true;
        return this;
    }

    UserBuilder inactive() {
        this.active = false;
        return this;
    }

    User build() {
        User user = new User(id, email, active);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }
}
