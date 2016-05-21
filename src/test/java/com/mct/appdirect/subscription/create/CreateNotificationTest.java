package com.mct.appdirect.subscription.create;

import com.mct.appdirect.utils.FakeServer;
import com.mct.appdirect.utils.FakeServerUtils;
import com.mct.appdirect.utils.Integrationtest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.mct.appdirect.subscription.create.CreateResponseBuilder.aSuccessfulResponseWithAccountIdentifier;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateNotificationTest extends Integrationtest {

    private FakeServer fakeServer;

    @Before
    public void setUp() throws Exception {
        fakeServer = FakeServerUtils.startFakeServerWithJsonResponse(this, "createEvent.json");
    }

    @Test
    public void shouldCreateAUserWithNotificationReceived() {
        ResponseEntity<CreateResponse> createResponse = callCreateNotificationWithUrlParam(fakeServer.getUrl());

        assertThat(createResponse.getStatusCode(), equalTo(HttpStatus.OK));

        CreateResponse expectedResponse = aSuccessfulResponseWithAccountIdentifier("1");
        assertThat(createResponse.getBody(), equalTo(expectedResponse));
    }

    private ResponseEntity<CreateResponse> callCreateNotificationWithUrlParam(String urlParam) {
        return template.getForEntity("http://localhost:" + port + "/subscription/create?url={url}", CreateResponse.class, urlParam);
    }

    @After
    public void tearDown() throws Exception {
        fakeServer.shutdown();
    }
}
