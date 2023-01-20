package ru.kata.spring.boot_security.demo.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

public interface UserService extends UserDetailsService {
  List<User> listUsers();

  void saveUser(User user);

  User findByUsername(String username);

  void removeUser(Long id);

  User getUser(Long id);

  void update(User user,Long id);


}
