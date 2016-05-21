package com.mct.appdirect.subscription.create;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateNotificationController {

    @RequestMapping("/subscription/create")
    public CreateResponse subscriptionCreate(@RequestParam(value = "url") String url) {
        return null;
    }

}
