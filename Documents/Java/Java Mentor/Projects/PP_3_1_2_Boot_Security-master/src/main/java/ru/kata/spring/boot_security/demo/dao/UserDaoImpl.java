package ru.kata.spring.boot_security.demo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

@Repository
public class UserDaoImpl implements UserDao {

  @PersistenceContext
  private EntityManager manager;
  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public List<User> listUsers() {
    TypedQuery<User> query = manager.createQuery("from User", User.class);
    return query.getResultList();
  }

  @Override
  @Transactional
  public User saveUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    manager.persist(user);
    return manager.find(User.class, user.getId());
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
  public User update(User user, Long id) {

    user.setId(user.getId());
    user.setPassword(
        passwordEncoder.matches(user.getPassword(), user.getPassword()) ?
            user.getPassword() : passwordEncoder.encode(user.getPassword()));

    manager.merge(user);
    return manager.find(User.class, user.getId());
  }


}
