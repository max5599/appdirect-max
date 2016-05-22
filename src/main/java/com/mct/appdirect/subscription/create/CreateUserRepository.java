package com.mct.appdirect.subscription.create;

import com.mct.appdirect.subscription.Event;

@FunctionalInterface
interface CreateUserRepository {

    UserCreationResult createUser(Event event);

}
