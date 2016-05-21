package com.mct.appdirect.subscription.create;

import java.util.Optional;

public class UserCreationResult {

    private final String userId;
    private final String errorCode;

    private UserCreationResult(String userId, String errorCode) {
        this.userId = userId;
        this.errorCode = errorCode;
    }

    public static UserCreationResult userCreationSuccedWithId(String userId) {
        return new UserCreationResult(userId, null);
    }

    public static UserCreationResult userCreationFailedWithError(String errorCode) {
        return new UserCreationResult(null, errorCode);
    }

    public Optional<String> getUserId() {
        return Optional.ofNullable(userId);
    }

    public Optional<String> getErrorCode() {
        return Optional.ofNullable(errorCode);
    }
}
