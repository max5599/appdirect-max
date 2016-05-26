package com.mct.appdirect.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
class UserController {

    private final FindUserRepository findUserRepository;

    @Autowired
    UserController(FindUserRepository findUserRepository) {
        this.findUserRepository = findUserRepository;
    }

    @RequestMapping("/users")
    public String findUsers() {
        return findUserRepository.getUsers().stream().map(this::formatUser).collect(Collectors.joining("\n"));
    }

    private String formatUser(User user) {
        return String.format("User %d with email %s is %s %s and he or she is %s",
                user.getId(),
                user.getEmail(),
                user.getFirstName().orElse("John"),
                user.getLastName().orElse("Doe"),
                (user.isCancelled() ? "inactive" : "active"));
    }
}