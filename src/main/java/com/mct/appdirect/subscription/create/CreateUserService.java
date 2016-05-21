package com.mct.appdirect.subscription.create;

@FunctionalInterface
public interface CreateUserService {

    CreateResponse createUserWithEventURL(String eventUrl);

}
