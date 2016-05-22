package com.mct.appdirect.subscription.create;

class CreateResponseBuilder {

    private CreateResponseBuilder() {
    }

    static CreateResponse aSuccessfulResponseWithAccountIdentifier(String accountIdentifier) {
        CreateResponse createResponse = new CreateResponse();
        createResponse.setSuccess(true);
        createResponse.setAccountIdentifier(accountIdentifier);
        return createResponse;
    }
}
