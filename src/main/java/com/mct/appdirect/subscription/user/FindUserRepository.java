package com.mct.appdirect.subscription.user;

import java.util.List;

@FunctionalInterface
public interface FindUserRepository {

    List<User> getUsers();

}
