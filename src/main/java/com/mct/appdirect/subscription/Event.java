package com.mct.appdirect.subscription;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Event {

    private final String type;
    private final Creator creator;
    private final Payload payload;

    @JsonCreator
    public Event(@JsonProperty(value = "type", required = true) String type,
                 @JsonProperty(value = "creator", required = true) Creator creator,
                 @JsonProperty(value = "payload", required = true) Payload payload) {
        this.type = type;
        this.creator = creator;
        this.payload = payload;
    }

    public Creator getCreator() {
        return creator;
    }

    public Payload getPayload() {
        return payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return Objects.equals(type, event.type) &&
                Objects.equals(creator, event.creator) &&
                Objects.equals(payload, event.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, creator, payload);
    }

    @Override
    public String toString() {
        return "Event{" +
                "type='" + type + '\'' +
                ", creator=" + creator +
                ", payload=" + payload +
                '}';
    }
}
