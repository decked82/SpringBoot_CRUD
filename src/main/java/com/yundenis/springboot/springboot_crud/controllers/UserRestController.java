package com.yundenis.springboot.springboot_crud.controllers;

import com.yundenis.springboot.springboot_crud.models.User;
import com.yundenis.springboot.springboot_crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getPrincipal(Authentication authentication) {
        String email = userService.getUsernameByName(authentication.getName()).getUsername();
        String roles = authentication.getAuthorities().toString();
        String res = email + " с ролями: " + roles;
        return res.replaceAll("role_", "");
    }

    @GetMapping("/user")
    public User showUser(Principal principal) {
        return userService.getUsernameByName(principal.getName());
    }
}
