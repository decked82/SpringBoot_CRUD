package com.yundenis.springboot.springboot_crud.controllers;

import com.yundenis.springboot.springboot_crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String showUser(Model model, Principal principal) {
        model.addAttribute("authorizedUser", userService.getUsernameByName(principal.getName()));
        return "user-profile";
    }
}
