package com.mct.appdirect.subscription.event;

import com.mct.appdirect.error.InvalidEventException;
import com.mct.appdirect.error.TransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import static java.lang.String.format;

@Service
public class NotificationEventRetrieverImpl implements NotificationEventRetriever {

    private final OAuthRestTemplate restTemplate;

    @Autowired
    public NotificationEventRetrieverImpl(OAuthRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Event retrieveEvent(String eventUrl) {
        try {
            return restTemplate.getForEntity(eventUrl, Event.class).getBody();
        } catch (HttpMessageNotReadableException e) {
            throw new InvalidEventException(format("Invalid event received for url %s", eventUrl), e);
        } catch (ResourceAccessException e) {
            throw new TransportException(format("Request timed out for url %s", eventUrl), e);
        }
    }
}
