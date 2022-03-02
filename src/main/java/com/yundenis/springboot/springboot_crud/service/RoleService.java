package com.yundenis.springboot.springboot_crud.service;


import com.yundenis.springboot.springboot_crud.models.Role;

import java.util.Set;

public interface RoleService {

    Set<Role> getRolesByName(String[] roles);

    void saveRole(Role role);
}
