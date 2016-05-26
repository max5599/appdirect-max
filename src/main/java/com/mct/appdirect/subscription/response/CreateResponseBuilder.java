package com.mct.appdirect.subscription.response;

public class CreateResponseBuilder {

    private CreateResponseBuilder() {
    }

    public static CreateResponse aSuccessfulResponseWithAccountIdentifier(String accountIdentifier) {
        return new CreateResponse(true, accountIdentifier);
    }
}
