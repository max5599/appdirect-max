package com.mct.appdirect.subscription.create;

import com.mct.appdirect.response.BaseResponse;

@FunctionalInterface
interface CreateUserService {

    BaseResponse createUserWithEventURL(String eventUrl);

}
