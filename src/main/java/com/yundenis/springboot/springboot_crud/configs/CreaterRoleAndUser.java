package com.yundenis.springboot.springboot_crud.configs;

import com.yundenis.springboot.springboot_crud.models.Role;
import com.yundenis.springboot.springboot_crud.models.User;
import com.yundenis.springboot.springboot_crud.service.RoleService;
import com.yundenis.springboot.springboot_crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class CreaterRoleAndUser {

    private Environment env;
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public CreaterRoleAndUser(Environment env, RoleService roleService, UserService userService) {
        this.env = env;
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void initialize() {
        if (!env.getProperty("spring.jpa.hibernate.ddl-auto").equals("update")) {
            roleService.saveRole(new Role("ROLE_ADMIN"));
            roleService.saveRole(new Role("ROLE_USER"));

            Set<Role> rolesForAdmin = roleService.getRolesByName(new String[]{"ROLE_ADMIN", "ROLE_USER"});
            Set<Role> rolesForUser = roleService.getRolesByName(new String[]{"ROLE_USER"});

            User admin = new User("Админ", "Админов", "1", 30, "admin@gmail.com");
            User user = new User("Юсер", "Юсеров", "2", 25, "user@gmail.com");

            admin.setRoles(rolesForAdmin);
            userService.saveUser(admin);

            user.setRoles(rolesForUser);
            userService.saveUser(user);
        }
    }
}
