package com.mct.appdirect.subscription.create;

import com.mct.appdirect.utils.FakeServer;
import com.mct.appdirect.utils.FakeServerUtils;
import com.mct.appdirect.utils.IntegrationTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Sql("classpath:db/user/cleanTables.sql")
public class CreateNotificationTest extends IntegrationTest {

    private FakeServer fakeServer;

    @Before
    public void setUp() throws Exception {
        fakeServer = FakeServerUtils.startFakeServerWithJsonResponse(this, "createEvent.json");
    }

    @Test
    public void shouldCreateAUserWithNotificationReceived() throws Exception {
        CreateResponse response = callCreateNotificationWithUrlParam(fakeServer.getUrl());

        assertThat(response.isSuccess(), equalTo(true));
        assertThat(response.getAccountIdentifier(), not(isEmptyOrNullString()));
    }

    private CreateResponse callCreateNotificationWithUrlParam(String urlParam) throws Exception {
        return securedGet(encodeParamAndCreateUrl("/subscription/create?url=", urlParam), CreateResponse.class);
    }

    @After
    public void tearDown() throws Exception {
        fakeServer.shutdown();
    }
}
