package com.mct.appdirect.subscription.create;

import com.mct.appdirect.response.BaseResponse;
import com.mct.appdirect.response.ErrorResponseBuilder;
import com.mct.appdirect.subscription.Event;
import com.mct.appdirect.subscription.NotificationEventRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class CreateUserServiceImpl implements CreateUserService {

    private final NotificationEventRetriever notificationEventRetriever;
    private final CreateUserRepository createUserRepository;

    @Autowired
    public CreateUserServiceImpl(NotificationEventRetriever notificationEventRetriever, CreateUserRepository createUserRepository) {
        this.notificationEventRetriever = notificationEventRetriever;
        this.createUserRepository = createUserRepository;
    }

    @Override
    public BaseResponse createUserWithEventURL(String eventUrl) {
        Event event = notificationEventRetriever.retrieveEvent(eventUrl);
        UserCreationResult result = createUserRepository.createUser(event);

        return result.map(CreateResponseBuilder::aSuccessfulResponseWithAccountIdentifier,
                ErrorResponseBuilder::aFailureResponseWithErrorCode);
    }
}
