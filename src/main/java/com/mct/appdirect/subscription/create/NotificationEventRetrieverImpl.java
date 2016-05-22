package com.mct.appdirect.subscription.create;

import com.mct.appdirect.error.InvalidEventException;
import com.mct.appdirect.error.TransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;

@Service
class NotificationEventRetrieverImpl implements NotificationEventRetriever {

    private final RestTemplate restTemplate;

    @Autowired
    public NotificationEventRetrieverImpl(@Value("${app.server.timeout}") int timeout) {
        this.restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory factory = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
        factory.setReadTimeout(timeout);
        factory.setConnectTimeout(timeout);
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
