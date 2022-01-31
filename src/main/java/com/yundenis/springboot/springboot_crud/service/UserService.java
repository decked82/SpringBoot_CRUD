package com.yundenis.springboot.springboot_crud.service;

import com.yundenis.springboot.springboot_crud.models.User;

import java.util.List;

public interface UserService {

    public List<User> showAllUsers();

    public void saveUser(User user);

    public User getUser(Long id);

    public void updateUser(User user);

    public void deleteUser(Long id);
}
