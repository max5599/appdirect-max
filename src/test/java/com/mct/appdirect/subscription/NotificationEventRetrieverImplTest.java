package com.mct.appdirect.subscription;

import com.mct.appdirect.Application;
import com.mct.appdirect.error.InvalidEventException;
import com.mct.appdirect.error.TransportException;
import com.mct.appdirect.utils.FakeServer;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.mct.appdirect.subscription.EventBuilder.anEvent;
import static com.mct.appdirect.utils.FakeServerUtils.startFakeOAuthServerWithJsonResponse;
import static com.mct.appdirect.utils.FakeServerUtils.startFakeServerThatNeverRespond;
import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class NotificationEventRetrieverImplTest {

    @Autowired
    private NotificationEventRetrieverImpl eventRetriever;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private FakeServer fakeServer;

    @Test
    public void shouldCallUrlWithOAuthAuthorizationAndReturnedParsedBody() throws Exception {
        fakeServer = startFakeOAuthServerWithJsonResponse(this, "validEvent.json");

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

    @Test
    public void shouldThrowAHttpMessageNotReadableExceptionWhenEventIsInvalid() throws Exception {
        assertThatAnInvalidEventExceptionIsThrownForResponse("invalidEvent.json");
    }

    @Test
    public void shouldThrowAJsonParseExceptionWhenResponseIsInvalid() throws Exception {
        assertThatAnInvalidEventExceptionIsThrownForResponse("invalid.json");
    }

    private void assertThatAnInvalidEventExceptionIsThrownForResponse(String jsonLocation) throws Exception {
        fakeServer = startFakeOAuthServerWithJsonResponse(this, jsonLocation);
        String eventUrl = fakeServer.getUrl();
        String message = format("Invalid event received for url %s", eventUrl);

        assertThatExceptionOccurredForUrl(InvalidEventException.class, message);
    }

    @Test(timeout = 5000)
    public void shouldThrowATransportExceptionWhenConnectionTimedOut() throws Exception {
        fakeServer = startFakeServerThatNeverRespond();
        String eventUrl = fakeServer.getUrl();
        String message = format("Request timed out for url %s", eventUrl);

        assertThatExceptionOccurredForUrl(TransportException.class, message);
    }

    private void assertThatExceptionOccurredForUrl(Class<? extends Throwable> clazz, String message) {
        exception.expect(clazz);
        exception.expectMessage(message);

        eventRetriever.retrieveEvent(fakeServer.getUrl());
    }

    @After
    public void tearDown() throws Exception {
        fakeServer.shutdown();
    }
}