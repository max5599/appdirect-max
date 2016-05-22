package com.mct.appdirect.subscription.cancel;

import com.mct.appdirect.response.BaseResponse;

@FunctionalInterface
public interface CancelUserService {

    BaseResponse cancelUserWithEventURL(String eventUrl);

}
