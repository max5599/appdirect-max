package com.mct.appdirect.subscription.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserServiceImpl implements CreateUserService {

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
        String userId = createUserRepository.createUser(event);

        return new CreateResponse(true, userId);
    }
}
