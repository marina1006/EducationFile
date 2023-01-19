package ru.kata.spring.boot_security.demo.dao;

import java.util.List;
import ru.kata.spring.boot_security.demo.model.User;

public interface UserDao {

  List<User> listUsers();

  void saveUser(User user);

  User findByUsername(String username);

  void removeUser(Long id);

  User getUser(Long id);

  void update(User user,Long id);



}
