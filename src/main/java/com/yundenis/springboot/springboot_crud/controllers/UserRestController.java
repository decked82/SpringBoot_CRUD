package com.yundenis.springboot.springboot_crud.controllers;

import com.yundenis.springboot.springboot_crud.models.User;
import com.yundenis.springboot.springboot_crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;
import java.util.stream.Collectors;

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
        Collection<GrantedAuthority> roles = authentication.getAuthorities().stream().map(r ->
                new SimpleGrantedAuthority(r.getAuthority())).collect(Collectors.toList());
        String res = email + " с ролями: " + roles;
        return res.replaceAll("ROLE_", "");
    }

    @GetMapping("/user")
    public User showUser(Principal principal) {
        return userService.getUsernameByName(principal.getName());
    }
}
