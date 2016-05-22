package com.mct.appdirect.subscription.create;

import com.mct.appdirect.utils.FakeServer;
import com.mct.appdirect.utils.FakeServerUtils;
import org.junit.After;
import org.junit.Test;
import org.springframework.http.converter.HttpMessageNotReadableException;

import static com.mct.appdirect.subscription.create.EventBuilder.anEvent;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class NotificationEventRetrieverImplTest {

    private final NotificationEventRetrieverImpl eventRetriever = new NotificationEventRetrieverImpl();

    private FakeServer fakeServer;

    @Test
    public void shouldCallUrlAndReturnedParsedBody() throws Exception {
        fakeServer = FakeServerUtils.startFakeServerWithJsonResponse(this, "createEvent.json");

        String eventUrl = fakeServer.getUrl();
        Event event = eventRetriever.retrieveEvent(eventUrl);

        Event returnedEvent = anEvent()
                .withType("SUBSCRIPTION_ORDER")
                .withEmail("sampletester@testco.com")
                .withFirstName("Sample")
                .withLastName("Tester")
                .build();
        assertThat(event, equalTo(returnedEvent));
    }

    @Test(expected = HttpMessageNotReadableException.class)
    public void shouldThrowAHttpMessageNotReadableExceptionWhenEventIsInvalid() throws Exception {
        fakeServer = FakeServerUtils.startFakeServerWithJsonResponse(this, "invalidEvent.json");

        String eventUrl = fakeServer.getUrl();
        eventRetriever.retrieveEvent(eventUrl);
    }

    @Test(expected = HttpMessageNotReadableException.class)
    public void shouldThrowAJsonParseExceptionWhenResponseIsInvalid() throws Exception {
        fakeServer = FakeServerUtils.startFakeServerWithJsonResponse(this, "invalid.json");

        String eventUrl = fakeServer.getUrl();
        eventRetriever.retrieveEvent(eventUrl);
    }

    @After
    public void tearDown() throws Exception {
        fakeServer.shutdown();
    }
}