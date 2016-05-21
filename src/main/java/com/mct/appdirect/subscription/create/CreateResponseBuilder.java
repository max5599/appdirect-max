package com.mct.appdirect.subscription.create;

public class CreateResponseBuilder {

    private CreateResponseBuilder() {
    }

    public static CreateResponse aSuccessfulResponseWithAccountIdentifier(String accountIdentifier) {
        return new CreateResponse(true, accountIdentifier, null);
    }

    public static CreateResponse aFaliureResponseWithErrorCode(String errorCode) {
        return new CreateResponse(false, null, errorCode);
    }
}
