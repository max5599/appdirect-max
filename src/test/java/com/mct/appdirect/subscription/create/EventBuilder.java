package com.mct.appdirect.subscription.create;

public class EventBuilder {

    private String type;

    private EventBuilder(){}

    public static EventBuilder anEvent() {
        return new EventBuilder();
    }

    public EventBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public Event build() {
        Event event = new Event();
        event.setType(type);
        return event;
    }

}
