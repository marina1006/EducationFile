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

  public void saveUser(User user) {

    dao.saveUser(user);
  }

  public void removeUser(Long id) {

    dao.removeUser(id);

  }

  public void update(User user,Long id) {

    dao.update(user,id);

  }

  public User findByUsername(String name) {
    return listUsers().stream().filter(user -> user.getUsername().equals(name)).findAny()
        .orElse(null);
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
   User user = dao.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    System.out.println(user.getRoles());
    return user;
  }

}
