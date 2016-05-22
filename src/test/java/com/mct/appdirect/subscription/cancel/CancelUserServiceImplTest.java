package com.mct.appdirect.subscription.cancel;

import com.mct.appdirect.response.BaseResponse;
import com.mct.appdirect.response.ErrorResponse;
import org.junit.Test;

import static com.mct.appdirect.response.BaseResponseBuilder.aSuccessfulResponse;
import static com.mct.appdirect.response.ErrorResponseBuilder.aFailureResponseWithErrorCode;
import static com.mct.appdirect.subscription.EventBuilder.anEvent;
import static java.util.Optional.ofNullable;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class CancelUserServiceImplTest {

    @Test
    public void shouldReturnASuccessfulResponseIfCancellationSucceed() {
        CancelUserServiceImpl cancelUserService = createCancelServiceWhichReturnError(null);

        BaseResponse response = cancelUserService.cancelUserWithEventURL("eventURL");

        BaseResponse expectedResponse = aSuccessfulResponse();
        assertThat(response, equalTo(expectedResponse));
    }

    @Test
    public void shouldReturnAFailureWithErrorCodeIfCancellationFailed() {
        CancelUserServiceImpl cancelUserService = createCancelServiceWhichReturnError("USER_ALREADY_EXISTS");

        BaseResponse response = cancelUserService.cancelUserWithEventURL("eventURL");

        ErrorResponse expectedResponse = aFailureResponseWithErrorCode("USER_ALREADY_EXISTS");
        assertThat(response, equalTo(expectedResponse));
    }

    private CancelUserServiceImpl createCancelServiceWhichReturnError(String error) {
        return new CancelUserServiceImpl(eventUrl -> anEvent().build(), event -> ofNullable(error));
    }
}