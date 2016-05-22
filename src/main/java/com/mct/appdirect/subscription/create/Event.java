package com.mct.appdirect.subscription.create;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

class Event {

    private final String type;
    private final Creator creator;

    @JsonCreator
    public Event(@JsonProperty(value = "type", required = true) String type,
                 @JsonProperty(value = "creator", required = true) Creator creator) {
        this.type = type;
        this.creator = creator;
    }

    Creator getCreator() {
        return creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(type, event.type) &&
                Objects.equals(creator, event.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, creator);
    }

    @Override
    public String toString() {
        return "Event{" +
                "type='" + type + '\'' +
                ", creator=" + creator +
                '}';
    }
}
