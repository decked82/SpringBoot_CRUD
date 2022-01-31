package com.yundenis.springboot.springboot_crud.service;


import com.yundenis.springboot.springboot_crud.models.User;

import java.util.List;

public interface UserService {

    List<User> showAllUsers();

    void saveUser(User user, String[] roles);

    User getUser(Long id);

    User getUsernameByName(String name);

    void updateUser(User user, String[] roles);

    void deleteUser(Long id);
}
