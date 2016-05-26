package com.mct.appdirect.subscription.response;

public class BaseResponseBuilder {

    private BaseResponseBuilder() {
    }

    public static BaseResponse aSuccessfulResponse() {
        return new BaseResponse(true);
    }
}
