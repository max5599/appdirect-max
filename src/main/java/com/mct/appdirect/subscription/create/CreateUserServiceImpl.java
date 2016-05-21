package com.mct.appdirect.subscription.create;

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
    public CreateResponse createUserWithEventURL(String eventUrl) {
        Event event = notificationEventRetriever.retrieveEvent(eventUrl);
        UserCreationResult result = createUserRepository.createUser(event);

        return createSuccessfulResponseWithIdOrCreateFailureWithErrorCode(result);
    }

    private CreateResponse createSuccessfulResponseWithIdOrCreateFailureWithErrorCode(UserCreationResult result) {
        return result.getUserId().map(CreateResponseBuilder::aSuccessfulResponseWithAccountIdentifier)
        .orElseGet(() -> result.getErrorCode().map(CreateResponseBuilder::aFailureResponseWithErrorCode).get());
    }
}
