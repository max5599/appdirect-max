package com.mct.appdirect.subscription.create;

@FunctionalInterface
public interface CreateUserRepository {

    String createUser(Event event);

}
