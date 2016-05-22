package com.mct.appdirect.subscription.create;

class EventBuilder {

    private String type;
    private String email;
    private String firstName;
    private String lastName;

    private EventBuilder(){}

    static EventBuilder anEvent() {
        return new EventBuilder();
    }

    EventBuilder withType(String type) {
        this.type = type;
        return this;
    }

    EventBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    EventBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    EventBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    Event build() {
        Creator creator = new Creator(email);
        creator.setFirstName(firstName);
        creator.setLastName(lastName);
        return new Event(type, creator);
    }
}
