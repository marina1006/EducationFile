package ru.kata.spring.boot_security.demo.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  public UserService(UserRepository userRepository, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;

  }

  public List<User> findAll() {

    return userRepository.findAll();

  }

  public User finById(Long id) {

    return userRepository.getReferenceById(id);
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

  public void update(Long id, User user) {
    user.setId(id);
    userRepository.save(user);
  }

  public User findByUsername(String name) {
    return findAll().stream().filter(user -> user.getUsername().equals(name)).findAny()
        .orElse(null);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    return user;
  }

}
