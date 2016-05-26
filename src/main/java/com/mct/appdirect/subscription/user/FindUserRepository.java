package com.mct.appdirect.subscription.user;

import java.util.List;

@FunctionalInterface
interface FindUserRepository {

    List<User> getUsers();

}
