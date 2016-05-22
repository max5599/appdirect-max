package com.mct.appdirect.subscription.create;

import com.mct.appdirect.utils.FakeServer;
import com.mct.appdirect.utils.FakeServerUtils;
import com.mct.appdirect.utils.IntegrationTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CreateNotificationTest extends IntegrationTest {

    private FakeServer fakeServer;

    @Before
    public void setUp() throws Exception {
        fakeServer = FakeServerUtils.startFakeServerWithJsonResponse(this, "createEvent.json");
    }

    @Test
    public void shouldCreateAUserWithNotificationReceived() {
        ResponseEntity<CreateResponse> response = callCreateNotificationWithUrlParam(fakeServer.getUrl());

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        CreateResponse createResponse = response.getBody();
        assertThat(createResponse.isSuccess(), equalTo(true));
        assertThat(createResponse.getAccountIdentifier(), not(isEmptyOrNullString()));
    }

    private ResponseEntity<CreateResponse> callCreateNotificationWithUrlParam(String urlParam) {
        return template.getForEntity("http://localhost:" + port + "/subscription/create?url={url}", CreateResponse.class, urlParam);
    }

    @After
    public void tearDown() throws Exception {
        fakeServer.shutdown();
    }
}
