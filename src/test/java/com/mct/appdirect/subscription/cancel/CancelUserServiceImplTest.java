package com.mct.appdirect.subscription.cancel;

import com.mct.appdirect.response.BaseResponse;
import org.junit.Test;

import static com.mct.appdirect.response.BaseResponseBuilder.aSuccessfulResponse;
import static com.mct.appdirect.subscription.EventBuilder.anEvent;
import static java.util.Optional.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class CancelUserServiceImplTest {

    @Test
    public void shouldReturnASuccessfulResponseIfCancellationSucceed() {
        CancelUserServiceImpl cancelUserService = new CancelUserServiceImpl(eventUrl -> anEvent().build(), event -> empty());

        BaseResponse response = cancelUserService.cancelUserWithEventURL("eventURL");

        BaseResponse expectedResponse = aSuccessfulResponse();
        assertThat(response, equalTo(expectedResponse));
    }
}