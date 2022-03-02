package com.yundenis.springboot.springboot_crud.dao;

import com.yundenis.springboot.springboot_crud.models.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Role> getRolesByName(String[] roles) {
        return entityManager.createQuery("select r from Role r where r.role in (:roles)", Role.class)
                .setParameter("roles", Arrays.asList(roles)).getResultList()
                .stream().collect(Collectors.toSet());
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }
}
