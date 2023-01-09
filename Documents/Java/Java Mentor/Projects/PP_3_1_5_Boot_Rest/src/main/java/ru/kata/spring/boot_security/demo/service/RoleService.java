package ru.kata.spring.boot_security.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

@Service
@Transactional(readOnly = true)
public class RoleService {
  private final RoleRepository roleRepository;

  public RoleService(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  public List<Role> listRole() {

    return roleRepository.findAll();

  }

  public Role getRole(Long id) {
   Role role = roleRepository.getById(id);
    return role;
  }

  public void saveRole(Role role) {

    roleRepository.save(role);
  }

  public void removeRole(Long id) {

    roleRepository.deleteById(id);

  }

  public void update(Long id, User user) {
    user.setUsername(user.getUsername());
    user.setSurname(user.getSurname());
    user.setEmail(user.getEmail());
  }

}
