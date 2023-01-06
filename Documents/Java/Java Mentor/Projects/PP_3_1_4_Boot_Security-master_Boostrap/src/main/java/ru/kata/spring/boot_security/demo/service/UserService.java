package ru.kata.spring.boot_security.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  public UserService(UserRepository userRepository, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }
  @Transactional(readOnly = true)
  public List<User> listUsers() {

    return userRepository.findAll();

  }
  @Transactional(readOnly = true)
  public User getUser(Long id) {

    return userRepository.getById(id);
  }

  @Transactional
  public void saveUser(User user) {
    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

    userRepository.save(user);
  }
@Transactional
  public void removeUser(Long id) {

    userRepository.deleteById(id);

  }
  @Transactional
  public void update(Long id, User user) {
    user.setId(id);
    userRepository.save(user);
  }
  @Transactional
  public User findByUsername(String name) {
    return listUsers().stream().filter(user -> user.getUsername().equals(name)).findAny()
        .orElse(null);
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    System.out.println(user.getRoles());
    return user;
  }

}
