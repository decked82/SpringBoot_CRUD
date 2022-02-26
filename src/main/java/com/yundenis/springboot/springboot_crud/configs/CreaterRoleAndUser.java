package com.yundenis.springboot.springboot_crud.configs;

import com.yundenis.springboot.springboot_crud.models.Role;
import com.yundenis.springboot.springboot_crud.models.User;
import com.yundenis.springboot.springboot_crud.service.RoleService;
import com.yundenis.springboot.springboot_crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CreaterRoleAndUser {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public CreaterRoleAndUser(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void initialize() {

        roleService.saveRole(new Role("ROLE_ADMIN"));
        roleService.saveRole(new Role("ROLE_USER"));
        User admin = new User("Админ", "Админов", "1", 30, "admin@gmail.com");
        User user = new User("Юсер", "Юсеров", "2", 25, "user@gmail.com");
        userService.saveUser(admin, new String[]{"ROLE_ADMIN", "ROLE_USER"});
        userService.saveUser(user, new String[]{"ROLE_USER"});
    }
}
