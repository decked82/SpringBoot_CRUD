package com.yundenis.springboot.springboot_crud.service;

import com.yundenis.springboot.springboot_crud.dao.UserDao;
import com.yundenis.springboot.springboot_crud.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, RoleService roleService, UserDao userDao) {
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> showAllUsers() {
        return userDao.showAllUsers();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveUser(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUsernameByName(String name) {
        return userDao.getUsernameByName(name);
    }

    @Transactional
    @Override
    public void updateUser(User updatedUser) {
        if (!updatedUser.getPassword().isEmpty()) {
            if (!passwordEncoder.matches(userDao.getUser(updatedUser.getId()).getPassword(),
                    passwordEncoder.encode(updatedUser.getPassword()))) {
                updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }
        } else {
            updatedUser.setPassword(userDao.getUser(updatedUser.getId()).getPassword());
        }
        userDao.updateUser(updatedUser);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Transactional
    @Override
    public void createDefqultUsers(User user, String[] roles) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roleService.getRolesByName(roles));
        userDao.saveUser(user);
    }
}
