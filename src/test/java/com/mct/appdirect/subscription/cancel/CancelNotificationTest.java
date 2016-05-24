package com.mct.appdirect.subscription.cancel;

import com.mct.appdirect.response.BaseResponse;
import com.mct.appdirect.utils.FakeServer;
import com.mct.appdirect.utils.FakeServerUtils;
import com.mct.appdirect.utils.IntegrationTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.OK;

@Sql({"classpath:db/user/insertUser1.sql"})
public class CancelNotificationTest extends IntegrationTest {

    private FakeServer fakeServer;

    @Before
    public void setUp() throws Exception {
        fakeServer = FakeServerUtils.startFakeServerWithJsonResponse(this, "cancelEvent.json");
    }

    @Test
    public void shouldCreateAUserWithNotificationReceived() {
        ResponseEntity<BaseResponse> response = callCancelNotificationWithUrlParam(fakeServer.getUrl());

        assertThat(response.getStatusCode(), equalTo(OK));

        BaseResponse baseResponse = response.getBody();
        assertThat(baseResponse.isSuccess(), equalTo(true));
    }

    private ResponseEntity<BaseResponse> callCancelNotificationWithUrlParam(String urlParam) {
        return template.getForEntity(urlForPath("/subscription/cancel?url={url}"), BaseResponse.class, urlParam);
    }

    @After
    public void tearDown() throws Exception {
        fakeServer.shutdown();
    }
}
