package com.mct.appdirect.subscription.create;

@FunctionalInterface
public interface NotificationEventRetriever {

    Event retrieveEvent(String eventUrl);

}
