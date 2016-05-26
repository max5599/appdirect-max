package com.mct.appdirect.subscription.create;

import com.mct.appdirect.subscription.event.Event;

@FunctionalInterface
interface CreateUserRepository {

    UserCreationResult createUser(Event event);

}
