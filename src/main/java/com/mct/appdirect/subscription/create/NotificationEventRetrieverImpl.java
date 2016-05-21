package com.mct.appdirect.subscription.create;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationEventRetrieverImpl implements NotificationEventRetriever {

    private final RestTemplate restTemplate;

    public NotificationEventRetrieverImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Event retrieveEvent(String eventUrl) {
        return restTemplate.getForEntity(eventUrl, Event.class).getBody();
    }
}
