package com.mct.appdirect.subscription.create;

public class CreateResponseBuilder {

    private boolean success = false;
    private String accountIdentifier = "accountIdentifier";

    private CreateResponseBuilder() {}

    public static CreateResponseBuilder aCreateResponse() {
        return new CreateResponseBuilder();
    }

    public CreateResponseBuilder withSuccess() {
        success = true;
        return this;
    }

    public CreateResponseBuilder withAccountIdentifier(String accountIdentifier) {
        this.accountIdentifier = accountIdentifier;
        return this;
    }

    public CreateResponse build() {
        CreateResponse response =  new CreateResponse();
        response.setSuccess(success);
        response.setAccountIdentifier(accountIdentifier);
        return response;
    }

}
