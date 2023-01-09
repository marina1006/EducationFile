package ru.kata.spring.boot_security.demo.dao;

import java.util.List;
import ru.kata.spring.boot_security.demo.model.User;

public interface UserDao {

  List<User> listUsers();

  User saveUser(User user);

  void removeUser(Long id);

  User getUser(Long id);

  User update(User user,Long id);



}
