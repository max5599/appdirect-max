package com.mct.appdirect.subscription.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionSecurityController {

    @RequestMapping("/subscription/security")
    public String helloAuthorizedUser() {
        return "Hello";
    }
}
