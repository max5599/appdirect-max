package com.mct.appdirect.subscription.create;

import com.mct.appdirect.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class CreateNotificationController {

    private final Validator<String> urlValidator;
    private final CreateUserService createUserService;

    @Autowired
    public CreateNotificationController(Validator<String> urlValidator, CreateUserService createUserService) {
        this.urlValidator = urlValidator;
        this.createUserService = createUserService;
    }

    @RequestMapping("/subscription/create")
    public CreateResponse subscriptionCreate(@RequestParam(value = "url") String url) {
        if (!urlValidator.isValid(url)) {
            throw new IllegalArgumentException("The 'url' parameter is not a valid url");
        }

        return createUserService.createUserWithEventURL(url);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleBadRequests(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

}
