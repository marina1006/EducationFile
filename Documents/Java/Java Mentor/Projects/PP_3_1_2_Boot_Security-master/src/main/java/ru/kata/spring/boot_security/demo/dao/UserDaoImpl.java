package ru.kata.spring.boot_security.demo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;
@Repository
public class UserDaoImpl implements UserDao {

  @PersistenceContext
  private EntityManager manager;

  public List<User> listUsers() {
    TypedQuery<User> query = manager.createQuery("from User", User.class);
    return query.getResultList();
  }

  @Override
  public void saveUser(User user) {

    manager.persist(user);
  }

  @Override
  public void removeUser(Long id) {

    manager.remove(manager.find(User.class, id));

  }

  @Override
  public User getUser(Long id) {

    return manager.find(User.class, id);
  }

  @Override
  public void update(Long id, User user) {

    manager.merge(user);
  }

  @Override
  public User findByUsername(String username) {
    User user = new User();
    return new ru.kata.spring.boot_security.demo.model.User(
        user.getUsername(), user.getPassword(),
        user.getRoles());
  }

}
