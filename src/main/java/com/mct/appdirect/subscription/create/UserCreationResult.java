package com.mct.appdirect.subscription.create;

import java.util.Optional;

class UserCreationResult {

    private final String userId;
    private final String errorCode;

    private UserCreationResult(String userId, String errorCode) {
        this.userId = userId;
        this.errorCode = errorCode;
    }

    static UserCreationResult userCreationSuccedWithId(String userId) {
        return new UserCreationResult(userId, null);
    }

    static UserCreationResult userCreationFailedWithError(String errorCode) {
        return new UserCreationResult(null, errorCode);
    }

    Optional<String> getUserId() {
        return Optional.ofNullable(userId);
    }

    Optional<String> getErrorCode() {
        return Optional.ofNullable(errorCode);
    }
}
