package com.yundenis.springboot.springboot_crud.dao;


import com.yundenis.springboot.springboot_crud.models.Role;

import java.util.Set;

public interface RoleDao {

    Set<Role> getAllRoles();

    Set<Role> getRolesByName(String[] roles);

    void saveRole(Role role);

}
