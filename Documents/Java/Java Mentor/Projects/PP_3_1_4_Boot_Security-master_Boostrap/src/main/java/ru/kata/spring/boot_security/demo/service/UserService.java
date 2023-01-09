package ru.kata.spring.boot_security.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;


@Service
public class UserService implements UserDetailsService {

  @Autowired
  private final UserDao dao;

  public UserService(UserDao dao) {
    this.dao = dao;
  }

  @Transactional(readOnly = true)
  public List<User> listUsers() {

    return dao.listUsers();

  }

  @Transactional(readOnly = true)
  public User getUser(Long id) {

    return dao.getUser(id);
  }

  @Transactional
  public void saveUser(User user) {
    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

    dao.saveUser(user);
  }

  @Transactional
  public void removeUser(Long id) {

    dao.removeUser(id);

  }

  @Transactional
  public void update(Long id, User user) {
    user.setId(id);
    dao.saveUser(user);
  }

  @Transactional
  public User findByUsername(String name) {
    return listUsers().stream().filter(user -> user.getUsername().equals(name)).findAny()
        .orElse(null);
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    System.out.println(user.getRoles());
    return user;
  }

}
