package ru.kata.spring.boot_security.demo.service;

import java.security.Principal;
import java.util.List;
import javax.security.auth.x500.X500Principal;
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

  private final UserDao dao;

  public UserService(UserDao dao) {
    this.dao = dao;
  }

  public List<User> listUsers() {

    return dao.listUsers();

  }

  public User getUser(Long id) {

    return dao.getUser(id);
  }

  @Transactional
  public User saveUser(User user) {
    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

   return dao.saveUser(user);
  }

  public void removeUser(Long id) {

    dao.removeUser(id);

  }

  public User update(Long id, User user) {
    user.setId(id);
    dao.saveUser(user);
    return user;
  }

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
