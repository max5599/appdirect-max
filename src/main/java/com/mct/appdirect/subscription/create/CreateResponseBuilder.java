package com.mct.appdirect.subscription.create;

class CreateResponseBuilder {

    private CreateResponseBuilder() {
    }

    static CreateResponse aSuccessfulResponseWithAccountIdentifier(String accountIdentifier) {
        return new CreateResponse(true, accountIdentifier, null);
    }

    static CreateResponse aFailureResponseWithErrorCode(String errorCode) {
        return new CreateResponse(false, null, errorCode);
    }
}
