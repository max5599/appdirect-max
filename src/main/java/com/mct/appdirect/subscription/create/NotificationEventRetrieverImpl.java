package com.mct.appdirect.subscription.create;

import com.mct.appdirect.error.InvalidEventException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
class NotificationEventRetrieverImpl implements NotificationEventRetriever {

    private final RestTemplate restTemplate;

    public NotificationEventRetrieverImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public Event retrieveEvent(String eventUrl) {
        try {
            return restTemplate.getForEntity(eventUrl, Event.class).getBody();
        }catch (HttpMessageNotReadableException e) {
            throw new InvalidEventException(String.format("Invalid event received for url %s", eventUrl), e);
        }
    }
}
