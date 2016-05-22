package com.mct.appdirect.subscription.create;

import com.mct.appdirect.error.InvalidEventException;
import com.mct.appdirect.utils.FakeServer;
import com.mct.appdirect.utils.FakeServerUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.mct.appdirect.subscription.create.EventBuilder.anEvent;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class NotificationEventRetrieverImplTest {

    private final NotificationEventRetrieverImpl eventRetriever = new NotificationEventRetrieverImpl();

    @Rule
    public ExpectedException exception = ExpectedException.none();

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

    @Test
    public void shouldThrowAHttpMessageNotReadableExceptionWhenEventIsInvalid() throws Exception {
        assertThatAnInvalidEventExceptionIsThrownForResponse("invalidEvent.json");
    }

    @Test
    public void shouldThrowAJsonParseExceptionWhenResponseIsInvalid() throws Exception {
        assertThatAnInvalidEventExceptionIsThrownForResponse("invalid.json");
    }

    private void assertThatAnInvalidEventExceptionIsThrownForResponse(String jsonLocation) throws Exception {
        fakeServer = FakeServerUtils.startFakeServerWithJsonResponse(this, jsonLocation);
        String eventUrl = fakeServer.getUrl();

        exception.expect(InvalidEventException.class);
        exception.expectMessage(String.format("Invalid event received for url %s", eventUrl));

        eventRetriever.retrieveEvent(eventUrl);
    }

    @After
    public void tearDown() throws Exception {
        fakeServer.shutdown();
    }
}