package com.mct.appdirect.subscription.create;

import com.mct.appdirect.utils.FileUtils;
import com.mct.appdirect.utils.Integrationtest;
import com.vtence.molecule.WebServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.mct.appdirect.subscription.create.CreateResponseBuilder.aCreateResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateNotificationTest extends Integrationtest {

    private final WebServer fakeServer = WebServer.create();

    @Before
    public void setUp() throws Exception {
        String json = FileUtils.readFileFromRelativePath(this, "createEvent.json");
        fakeServer.start(((request, response) -> response.done(json)));
    }

    @Test
    @Ignore("Work in progress")
    public void shouldCreateAUserWithNotificationReceived() {
        ResponseEntity<CreateResponse> createResponse = callCreateNotificationWithUrlParam(fakeServer.uri().toString());

        assertThat(createResponse.getStatusCode(), equalTo(HttpStatus.OK));

        CreateResponse expectedResponse = aCreateResponse().withSuccess().withAccountIdentifier("abcd").build();
        assertThat(createResponse.getBody(), equalTo(expectedResponse));
    }

    private ResponseEntity<CreateResponse> callCreateNotificationWithUrlParam(String urlParam) {
        return template.getForEntity("http://localhost:" + port + "/subscription/create?url={url}", CreateResponse.class, urlParam);
    }

    @After
    public void tearDown() throws Exception {
        fakeServer.stop();
    }
}
