package com.mct.appdirect.subscription.create;

import com.mct.appdirect.subscription.response.CreateResponse;
import com.mct.appdirect.utils.FakeServer;
import com.mct.appdirect.utils.FakeServerUtils;
import com.mct.appdirect.utils.IntegrationTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.HttpStatus.OK;

@Sql("classpath:db/user/cleanTables.sql")
public class CreateNotificationTest extends IntegrationTest {

    private FakeServer fakeServer;

    @Before
    public void setUp() throws Exception {
        fakeServer = FakeServerUtils.startFakeOAuthServerWithJsonResponse(this, "createEvent.json");
    }

    @Test
    public void shouldCreateAUserWithNotificationReceived() throws Exception {
        ResponseEntity<CreateResponse> response = callCreateNotificationWithUrlParam(fakeServer.getUrl());

        assertThat(response.getStatusCode(), equalTo(OK));
        assertThat(response.getBody().isSuccess(), equalTo(true));
        assertThat(response.getBody().getAccountIdentifier(), not(isEmptyOrNullString()));
    }

    private ResponseEntity<CreateResponse> callCreateNotificationWithUrlParam(String urlParam) throws Exception {
        return securedGet("/subscription/create?url={url}", CreateResponse.class, urlParam);
    }

    @After
    public void tearDown() throws Exception {
        fakeServer.shutdown();
    }
}
