package com.mct.appdirect.subscription.create;

import org.springframework.stereotype.Repository;

import static com.mct.appdirect.subscription.create.UserCreationResult.userCreationSucceedWithId;

@Repository
class CreateUserRepositoryImpl implements CreateUserRepository {

    @Override
    public UserCreationResult createUser(Event event) {
        return userCreationSucceedWithId("1");
    }
}
