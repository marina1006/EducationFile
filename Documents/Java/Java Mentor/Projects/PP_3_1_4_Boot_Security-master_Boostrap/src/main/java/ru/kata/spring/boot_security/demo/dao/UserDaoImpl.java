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

  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  @PersistenceContext
  private EntityManager manager;

  public List<User> listUsers() {
    TypedQuery<User> query = manager.createQuery("from User", User.class);
    return query.getResultList();
  }

  @Override
  @Transactional
  public void saveUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    manager.persist(user);
  }

  @Override
  @Transactional
  public void removeUser(Long id) {

    manager.remove(id);

  }

  @Override
  public User getUser(Long id) {

    return manager.find(User.class, id);
  }

  @Override
  @Transactional
  public void update( Long id,User user) {

    user.setId(user.getId());
    user.setUsername(user.getUsername());
    user.setSurname(user.getSurname());
    user.setEmail(user.getEmail());

    user.setPassword(
        passwordEncoder.matches(user.getPassword(), user.getPassword()) ?
            user.getPassword() : passwordEncoder.encode(user.getPassword()));

    manager.merge(user);

  }


}
