package com.mct.appdirect.subscription.create;

import java.util.Objects;
import java.util.Optional;

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

    Optional<String> getUserId() {
        return Optional.ofNullable(userId);
    }

    Optional<String> getErrorCode() {
        return Optional.ofNullable(errorCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreationResult that = (UserCreationResult) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(errorCode, that.errorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, errorCode);
    }

    @Override
    public String toString() {
        return "UserCreationResult{" +
                "userId='" + userId + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
