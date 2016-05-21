package com.mct.appdirect.subscription.create;

import java.util.Objects;

class Event {

    private String type;

    public Event() {

    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(type, event.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "Event{" +
                "type='" + type + '\'' +
                '}';
    }
}
