package com.mct.appdirect.subscription;

@FunctionalInterface
public interface NotificationEventRetriever {

    Event retrieveEvent(String eventUrl);

}
