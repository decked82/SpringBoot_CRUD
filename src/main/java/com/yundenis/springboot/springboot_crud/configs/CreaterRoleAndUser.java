package com.yundenis.springboot.springboot_crud.configs;

import com.yundenis.springboot.springboot_crud.models.Role;
import com.yundenis.springboot.springboot_crud.models.User;
import com.yundenis.springboot.springboot_crud.service.RoleService;
import com.yundenis.springboot.springboot_crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

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

        if (roleService.getRolesByName(new String[]{"ROLE_ADMIN", "ROLE_USER"}).isEmpty()) {
            roleService.saveRole(new Role("ROLE_ADMIN"));
            roleService.saveRole(new Role("ROLE_USER"));
        }

        Set<Role> rolesForAdmin = roleService.getRolesByName(new String[]{"ROLE_ADMIN", "ROLE_USER"});
        Set<Role> rolesForUser = roleService.getRolesByName(new String[]{"ROLE_USER"});

        User admin = new User("Админ", "Админов", "1", 30, "admin@gmail.com");
        User user = new User("Юсер", "Юсеров", "2", 25, "user@gmail.com");

        if (userService.getUsernameByName("admin@gmail.com")==null) {
            admin.setRoles(rolesForAdmin);
            userService.saveUser(admin);
        }

        if (userService.getUsernameByName("user@gmail.com")==null) {
            user.setRoles(rolesForUser);
            userService.saveUser(user);
        }
    }
}
