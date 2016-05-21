package com.mct.appdirect.subscription.create;

@FunctionalInterface
interface CreateUserService {

    CreateResponse createUserWithEventURL(String eventUrl);

}
