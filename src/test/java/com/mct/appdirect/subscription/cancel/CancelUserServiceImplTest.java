package com.mct.appdirect.subscription.cancel;

import com.mct.appdirect.subscription.event.NotificationEventRetriever;
import com.mct.appdirect.subscription.response.BaseResponse;
import org.junit.Test;

import static com.mct.appdirect.subscription.event.EventBuilder.anEvent;
import static com.mct.appdirect.subscription.response.BaseResponseBuilder.aSuccessfulResponse;
import static com.mct.appdirect.subscription.response.ErrorResponseBuilder.ACCOUNT_NOT_FOUND;
import static com.mct.appdirect.subscription.response.ErrorResponseBuilder.aFailureResponseWithErrorCode;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class CancelUserServiceImplTest {

    private NotificationEventRetriever eventWithAccountIdentifier = eventUrl -> anEvent().withAccountIdentifier("1").build();
    private NotificationEventRetriever eventWithoutAccount = eventUrl -> anEvent().build();

    private CancelUserRepository successfulCancellation = event -> empty();
    private CancelUserRepository cancellationFailedForAccountNotFound = event -> of(ACCOUNT_NOT_FOUND);

    @Test
    public void shouldReturnASuccessfulResponseIfEventIsValidAndCancellationSucceed() {
        CancelUserServiceImpl service = new CancelUserServiceImpl(eventWithAccountIdentifier, successfulCancellation);

        serviceMustReturn(service, aSuccessfulResponse());
    }

    @Test
    public void shouldReturnAFailureWithErrorCodeIfCancellationFailed() {
        CancelUserServiceImpl service = new CancelUserServiceImpl(eventWithAccountIdentifier, cancellationFailedForAccountNotFound);

        serviceMustReturn(service, aFailureResponseWithErrorCode(ACCOUNT_NOT_FOUND));
    }

    @Test
    public void shouldReturnAFailureIfThereNoAccountInTheEvent() {
        CancelUserServiceImpl service = new CancelUserServiceImpl(eventWithoutAccount, cancellationFailedForAccountNotFound);

        serviceMustReturn(service, aFailureResponseWithErrorCode(ACCOUNT_NOT_FOUND));
    }

    private void serviceMustReturn(CancelUserServiceImpl cancelUserService, BaseResponse expectedResponse) {
        BaseResponse response = cancelUserService.cancelUserWithEventURL("eventURL");

        assertThat(response, equalTo(expectedResponse));
    }
}