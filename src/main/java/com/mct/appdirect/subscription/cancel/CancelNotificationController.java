package com.mct.appdirect.subscription.cancel;

import com.mct.appdirect.response.BaseResponse;
import com.mct.appdirect.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CancelNotificationController {

    private final Validator<String> urlValidator;
    private final CancelUserService cancelUserService;

    @Autowired
    public CancelNotificationController(Validator<String> urlValidator, CancelUserService cancelUserService) {
        this.urlValidator = urlValidator;
        this.cancelUserService = cancelUserService;
    }

    @RequestMapping("/subscription/cancel")
    public BaseResponse cancelSubscription(@RequestParam(value = "url") String url) {
        if (urlValidator.isInvalid(url)) {
            throw new IllegalArgumentException("The 'url' parameter is not a valid url");
        }

        return cancelUserService.cancelUserWithEventURL(url);
    }
}
