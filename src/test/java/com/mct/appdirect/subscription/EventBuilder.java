package com.mct.appdirect.subscription;

public class EventBuilder {

    private String type;
    private String email;
    private String firstName;
    private String lastName;

    private EventBuilder(){}

    public static EventBuilder anEvent() {
        return new EventBuilder();
    }

    public EventBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public EventBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public EventBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public EventBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Event build() {
        Creator creator = new Creator(email);
        creator.setFirstName(firstName);
        creator.setLastName(lastName);
        return new Event(type, creator);
    }
}
