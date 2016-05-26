package com.mct.appdirect.subscription.event;

public class EventBuilder {

    private String type;
    private String email;
    private String firstName;
    private String lastName;
    private String accountIdentifier;

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

    public EventBuilder withAccountIdentifier(String accountIdentifier) {
        this.accountIdentifier = accountIdentifier;
        return this;
    }

    public Event build() {
        Creator creator = new Creator(email, firstName, lastName);
        Payload payload = new Payload();
        if (accountIdentifier != null) {
            payload.setAccount(new Account(accountIdentifier));
        }
        return new Event(type, creator, payload);
    }
}
