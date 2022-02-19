package com.yundenis.springboot.springboot_crud.controllers;

import com.yundenis.springboot.springboot_crud.models.User;
import com.yundenis.springboot.springboot_crud.service.RoleService;
import com.yundenis.springboot.springboot_crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String showUsers(Model model, Principal principal) {
        model.addAttribute("allUsers", userService.showAllUsers());
        model.addAttribute("authorizedUser", userService.getUsernameByName(principal.getName()));
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin-profile";
    }

    @GetMapping("/admin/new")
    public String newUser(@ModelAttribute("newUser") User user) {
        return "admin-profile";
    }

    @PostMapping("/admin/create")
    public String createUser(User user, @RequestParam(value = "roles") String[] roles) {
        userService.saveUser(user, roles);
        return "redirect:/admin";
    }

     @PutMapping("/admin")
    public String updateUser(User user, String[] roles) {
        userService.updateUser(user, roles);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
