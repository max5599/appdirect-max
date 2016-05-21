package com.mct.appdirect.subscription.create;

@FunctionalInterface
public interface CreateUserRepository {

    UserCreationResult createUser(Event event);

}
