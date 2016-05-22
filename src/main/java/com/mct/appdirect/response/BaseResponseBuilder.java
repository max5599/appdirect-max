package com.mct.appdirect.response;

public class BaseResponseBuilder {

    public static BaseResponse aSuccessfulResponse() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setSuccess(true);
        return baseResponse;
    }
}
