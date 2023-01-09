package ru.kata.spring.boot_security.demo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

  @PersistenceContext
  private EntityManager manager;

  public List<Role> listRoles() {
    TypedQuery<Role> query = manager.createQuery("from Role", Role.class);
    return query.getResultList();
  }

  @Transactional
  public void saveRole(Role role) {

    manager.persist(role);

  }

  @Transactional
  public void removeRole(Long id) {

    manager.remove(manager.find(Role.class, id));

  }

  public Role getRole(Long id) {

    return manager.find(Role.class, id);
  }

  @Transactional
  public void update(Role role, Long id) {

    role.setId(role.getId());

    manager.merge(role);

  }

}
