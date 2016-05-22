package com.mct.appdirect.subscription.cancel;

import com.mct.appdirect.response.BaseResponse;
import com.mct.appdirect.response.BaseResponseBuilder;
import com.mct.appdirect.response.ErrorResponseBuilder;
import com.mct.appdirect.subscription.Event;
import com.mct.appdirect.subscription.NotificationEventRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Optional<String> error = cancelUserRepository.cancelUserAndReturnErrorIfPresent(event);

        Optional<BaseResponse> errorResponse = error.map(ErrorResponseBuilder::aFailureResponseWithErrorCode);
        return errorResponse.orElseGet(BaseResponseBuilder::aSuccessfulResponse);
    }
}
