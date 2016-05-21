package com.mct.appdirect.subscription.create;

@FunctionalInterface
interface CreateUserRepository {

    UserCreationResult createUser(Event event);

}
