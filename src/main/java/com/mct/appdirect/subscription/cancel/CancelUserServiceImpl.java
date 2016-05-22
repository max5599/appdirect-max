package com.mct.appdirect.subscription.cancel;

import com.mct.appdirect.response.BaseResponse;
import com.mct.appdirect.subscription.NotificationEventRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.mct.appdirect.response.BaseResponseBuilder.aSuccessfulResponse;

@Service
public class CancelUserServiceImpl implements CancelUserService {

    private final NotificationEventRetriever notificationEventRetriever;
    private final CancelUserRepository cancelUserRepository;

    @Autowired
    public CancelUserServiceImpl(NotificationEventRetriever notificationEventRetriever, CancelUserRepository cancelUserRepository) {
        this.notificationEventRetriever = notificationEventRetriever;
        this.cancelUserRepository = cancelUserRepository;
    }

    @Override
    public BaseResponse cancelUserWithEventURL(String eventUrl) {
        return aSuccessfulResponse();
    }
}
