package com.mct.appdirect.subscription.cancel;

import com.mct.appdirect.subscription.response.BaseResponse;

@FunctionalInterface
interface CancelUserService {

    BaseResponse cancelUserWithEventURL(String eventUrl);

}
