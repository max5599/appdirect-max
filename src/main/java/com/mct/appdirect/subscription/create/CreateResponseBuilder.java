package com.mct.appdirect.subscription.create;

class CreateResponseBuilder {

    private CreateResponseBuilder() {
    }

    static CreateResponse aSuccessfulResponseWithAccountIdentifier(String accountIdentifier) {
        CreateResponse createResponse = new CreateResponse(true);
        createResponse.setAccountIdentifier(accountIdentifier);
        return createResponse;
    }
}
