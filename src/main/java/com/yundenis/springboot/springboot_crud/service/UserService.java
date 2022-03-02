package com.yundenis.springboot.springboot_crud.service;


import com.yundenis.springboot.springboot_crud.models.User;

import java.util.List;

public interface UserService {

    List<User> showAllUsers();

    void saveUser(User user);

    User getUsernameByName(String name);

    void updateUser(User user);

    void deleteUser(Long id);

    void createDefqultUsers(User user, String[] roles);
}
