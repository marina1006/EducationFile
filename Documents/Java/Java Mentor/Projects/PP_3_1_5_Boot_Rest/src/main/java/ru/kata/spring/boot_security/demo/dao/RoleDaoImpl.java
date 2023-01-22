package ru.kata.spring.boot_security.demo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

  @PersistenceContext
  private EntityManager manager;
  @Override
  public Set<Role> listRoles() {
    return new HashSet<>(
        manager.createQuery("FROM Role", Role.class).getResultList());
  }

  @Override
  public Role getRoleByName(String name) {
    return (Role) manager.createQuery("SELECT r FROM Role r WHERE r.name = :name")
        .setParameter("name", name).getResultList().get(0);
  }
  @Override
  @Transactional
  public void saveRole(Role role) {

    manager.persist(role);

  }
  @Override
  @Transactional
  public void removeRole(Long id) {

    manager.remove(manager.find(Role.class, id));

  }
  @Override
  public Role getRole(Long id) {

    return manager.find(Role.class, id);
  }

}
