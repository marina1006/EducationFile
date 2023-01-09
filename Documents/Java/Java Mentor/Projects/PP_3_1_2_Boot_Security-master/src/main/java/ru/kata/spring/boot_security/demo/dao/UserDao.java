package ru.kata.spring.boot_security.demo.dao;

import java.util.List;
import ru.kata.spring.boot_security.demo.model.User;

public interface UserDao {

  List<User> listUsers();

  void saveUser(User user);

  void removeUser(Long id);

  public User getUser(Long id);

  public void update(Long id, User user);

  User findByUsername(String username);

}
