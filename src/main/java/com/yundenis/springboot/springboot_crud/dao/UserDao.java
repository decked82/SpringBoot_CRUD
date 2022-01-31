package com.yundenis.springboot.springboot_crud.dao;


import com.yundenis.springboot.springboot_crud.models.User;

import java.util.List;

public interface UserDao {

    List<User> showAllUsers();

    void saveUser(User user);

    User getUser(Long id);

    User getUsernameByName(String name);

    void updateUser(User user);

    void deleteUser(Long id);
}
