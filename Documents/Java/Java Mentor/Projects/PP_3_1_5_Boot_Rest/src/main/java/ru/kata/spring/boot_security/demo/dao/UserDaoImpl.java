package ru.kata.spring.boot_security.demo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
  @Transactional
  public void saveUser(User user) {
    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

    manager.persist(user);

  }

  @Override
  public User findByUsername(String username) {
    return manager
        .createQuery("SELECT u FROM User u JOIN FETCH u.roles " +
            "WHERE u.username = :username", User.class)
        .setParameter("username", username)
        .getResultList().stream().findAny().orElse(null);
  }

  @Override
  @Transactional
  public void removeUser(Long id) {

    manager.remove(manager.find(User.class, id));

  }

  @Override
  public User getUser(Long id) {

    return manager.find(User.class, id);
  }

  @Override
  @Transactional
  public void update(User user, Long id) {

    User u = manager.find(User.class, id);
    u.setUsername(user.getUsername());
    u.setEmail(user.getEmail());
    u.setPassword(user.getPassword());
    manager.merge(user);

  }


}
