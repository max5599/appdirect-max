package com.mct.appdirect.subscription.cancel;

import com.mct.appdirect.subscription.Event;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class CancelUserRepositoryImpl implements CancelUserRepository {
    @Override
    public Optional<String> cancelUserAndReturnErrorIfPresent(Event event) {
        //TODO
        return null;
    }
}
