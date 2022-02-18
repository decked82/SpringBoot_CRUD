package com.yundenis.springboot.springboot_crud.dao;

import com.yundenis.springboot.springboot_crud.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> showAllUsers() {
        return entityManager.createQuery("SELECT DISTINCT u FROM User u join fetch u.roles", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUser(Long id) {
        return entityManager.createQuery("select u from User u join fetch u.roles where u.id=:id", User.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public User getUsernameByName(String email) {
        return entityManager.createQuery("select u from User u join fetch u.roles where u.username=:email", User.class)
                .setParameter("email", email).getResultStream().findFirst().orElse(null);
    }

    @Override
    public void updateUser(User updatedUser) {
        entityManager.merge(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(entityManager.find(User.class, id));

    }
}
