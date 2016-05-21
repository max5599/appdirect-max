package com.mct.appdirect.subscription.create;

@FunctionalInterface
interface NotificationEventRetriever {

    Event retrieveEvent(String eventUrl);

}
