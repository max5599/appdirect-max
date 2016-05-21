package com.mct.appdirect.subscription.create;

import com.mct.appdirect.utils.FakeServer;
import com.mct.appdirect.utils.FakeServerUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.mct.appdirect.subscription.create.EventBuilder.anEvent;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class NotificationEventRetrieverImplTest {

    private final NotificationEventRetrieverImpl eventRetriever = new NotificationEventRetrieverImpl();

    private FakeServer fakeServer;

    @Before
    public void setUp() throws Exception {
        fakeServer = FakeServerUtils.startFakeServerWithJsonResponse(this, "createEvent.json");
    }

    @Test
    public void shouldCallUrlAndReturnedParsedBody() throws Exception {
        String eventUrl = fakeServer.getUrl();
        Event returnedEvent = anEvent()
                .withType("SUBSCRIPTION_ORDER")
                .withEmail("sampletester@testco.com")
                .withFirstName("Sample")
                .withLastName("Tester")
                .build();

        assertThat(eventRetriever.retrieveEvent(eventUrl), equalTo(returnedEvent));
    }

    @After
    public void tearDown() throws Exception {
        fakeServer.shutdown();
    }
}