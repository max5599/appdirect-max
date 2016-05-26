package com.mct.appdirect.subscription.cancel;

import com.mct.appdirect.subscription.Account;
import com.mct.appdirect.subscription.Event;
import com.mct.appdirect.subscription.NotificationEventRetriever;
import com.mct.appdirect.subscription.response.BaseResponse;
import com.mct.appdirect.subscription.response.BaseResponseBuilder;
import com.mct.appdirect.subscription.response.ErrorResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.mct.appdirect.subscription.response.ErrorResponseBuilder.ACCOUNT_NOT_FOUND;
import static com.mct.appdirect.subscription.response.ErrorResponseBuilder.aFailureResponseWithErrorCode;

@Service
class CancelUserServiceImpl implements CancelUserService {

    private final NotificationEventRetriever notificationEventRetriever;
    private final CancelUserRepository cancelUserRepository;

    @Autowired
    public CancelUserServiceImpl(NotificationEventRetriever notificationEventRetriever, CancelUserRepository cancelUserRepository) {
        this.notificationEventRetriever = notificationEventRetriever;
        this.cancelUserRepository = cancelUserRepository;
    }

    @Override
    public BaseResponse cancelUserWithEventURL(String eventUrl) {
        Event event = notificationEventRetriever.retrieveEvent(eventUrl);

        Optional<Account> account = event.getPayload().getAccount();
        if (!account.isPresent()) {
            return aFailureResponseWithErrorCode(ACCOUNT_NOT_FOUND);
        }

        Optional<BaseResponse> errorResponse = cancelUserRepository.cancelUserAndReturnErrorIfPresent(account.get().getAccountIdentifier())
                .map(ErrorResponseBuilder::aFailureResponseWithErrorCode);

        return errorResponse.orElseGet(BaseResponseBuilder::aSuccessfulResponse);
    }
}
