package com.mct.appdirect.response;

public class BaseResponseBuilder {

    public static BaseResponse aSuccessfulResponse() {
        return new BaseResponse(true);
    }
}
