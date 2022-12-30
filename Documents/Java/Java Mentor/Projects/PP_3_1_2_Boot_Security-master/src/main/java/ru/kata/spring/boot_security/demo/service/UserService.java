package ru.kata.spring.boot_security.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

  public List<User> listUsers() {

    return userRepository.findAll();

  }
  public User getUser(Long id) {

    return userRepository.getById(id);
  }
  public void saveUser(User user) {

    userRepository.save(user);
  }

  public void removeUser(Long id) {

    userRepository.deleteById(id);

  }

  public void update(Long id, User user) {
    user.setName(user.getName());
    user.setSurname(user.getSurname());
    user.setEmail(user.getEmail());
  }

  public User findUser(String name) {
    return listUsers().stream().filter(user -> user.getLogin().equals(name)).findAny().orElse(null);
  }
  @Transactional
  public void register(User user) {
//    user.setPassword(passwordEncoder.encode(user.getPassword()));
//    user.setRole("ROLE_USER");
    userRepository.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);

    if (user == null)
      throw new UsernameNotFoundException("User not found");

    return user;
  }
}
