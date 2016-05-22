package com.mct.appdirect.subscription.create;

import java.util.function.Function;

class UserCreationResult {

    private final String userId;
    private final String errorCode;

    private UserCreationResult(String userId, String errorCode) {
        this.userId = userId;
        this.errorCode = errorCode;
    }

    static UserCreationResult userCreationSucceedWithId(String userId) {
        return new UserCreationResult(userId, null);
    }

    static UserCreationResult userCreationFailedWithError(String errorCode) {
        return new UserCreationResult(null, errorCode);
    }

    <T> T map(Function<String, ? extends T> mapUserId, Function<String, ? extends T> mapErrorCode) {
        if(userId != null) {
            return mapUserId.apply(userId);
        }else {
            return mapErrorCode.apply(errorCode);
        }
    }

    @Override
    public String toString() {
        return "UserCreationResult{" +
                "userId='" + userId + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
