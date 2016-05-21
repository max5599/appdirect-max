package com.mct.appdirect.subscription.create;

import java.util.Objects;

class Event {

    private String type;
    private Creator creator;

    void setType(String type) {
        this.type = type;
    }

    void setCreator(Creator creator) {
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
