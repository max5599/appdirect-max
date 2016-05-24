package com.mct.appdirect.subscription.cancel;

import com.mct.appdirect.response.BaseResponse;
import com.mct.appdirect.utils.FakeServer;
import com.mct.appdirect.utils.FakeServerUtils;
import com.mct.appdirect.utils.IntegrationTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Sql({"classpath:db/user/insertUser1.sql"})
public class CancelNotificationTest extends IntegrationTest {

    private FakeServer fakeServer;

    @Before
    public void setUp() throws Exception {
        fakeServer = FakeServerUtils.startFakeServerWithJsonResponse(this, "cancelEvent.json");
    }

    @Test
    public void shouldCreateAUserWithNotificationReceived() throws Exception {
        BaseResponse response = callCancelNotificationWithUrlParam(fakeServer.getUrl());

        assertThat(response.isSuccess(), equalTo(true));
    }

    private BaseResponse callCancelNotificationWithUrlParam(String urlParam) throws Exception {
        return securedGet(encodeParamAndCreateUrl("/subscription/cancel?url=", urlParam), BaseResponse.class);
    }

    @After
    public void tearDown() throws Exception {
        fakeServer.shutdown();
    }
}
