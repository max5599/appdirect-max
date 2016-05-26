package com.mct.appdirect.subscription.create;

import com.mct.appdirect.subscription.response.BaseResponse;
import com.mct.appdirect.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CreateNotificationController {

    private final Validator<String> urlValidator;
    private final CreateUserService createUserService;

    @Autowired
    public CreateNotificationController(Validator<String> urlValidator, CreateUserService createUserService) {
        this.urlValidator = urlValidator;
        this.createUserService = createUserService;
    }

    @RequestMapping("/subscription/create")
    public BaseResponse subscriptionCreate(@RequestParam(value = "url") String url) {
        if (urlValidator.isInvalid(url)) {
            throw new IllegalArgumentException("The 'url' parameter is not a valid url");
        }

        return createUserService.createUserWithEventURL(url);
    }
}
