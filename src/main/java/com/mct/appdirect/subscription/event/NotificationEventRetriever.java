package com.mct.appdirect.subscription.event;

@FunctionalInterface
public interface NotificationEventRetriever {

    Event retrieveEvent(String eventUrl);

}
