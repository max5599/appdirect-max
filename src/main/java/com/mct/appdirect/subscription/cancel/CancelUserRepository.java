package com.mct.appdirect.subscription.cancel;

import com.mct.appdirect.subscription.Event;

import java.util.Optional;

@FunctionalInterface
public interface CancelUserRepository {

    Optional<String> cancelUserAndReturnErrorIfPresent(Event event);
}
