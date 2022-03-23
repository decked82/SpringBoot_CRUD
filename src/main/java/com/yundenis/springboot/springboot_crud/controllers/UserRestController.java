package com.yundenis.springboot.springboot_crud.controllers;

import com.yundenis.springboot.springboot_crud.models.User;
import com.yundenis.springboot.springboot_crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/header")
    public ResponseEntity<User> getAuthentication(Authentication authentication) {
        User user = userService.getUsernameByName(authentication.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> showUser(Principal principal) {
        User user = userService.getUsernameByName(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
