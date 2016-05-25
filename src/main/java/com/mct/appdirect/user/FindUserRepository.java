package com.mct.appdirect.user;

import java.util.List;

@FunctionalInterface
interface FindUserRepository {

    List<User> getUsers();

}
