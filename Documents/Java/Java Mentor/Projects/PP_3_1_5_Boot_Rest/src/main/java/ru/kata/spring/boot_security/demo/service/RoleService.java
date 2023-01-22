package ru.kata.spring.boot_security.demo.service;

import java.util.Set;
import ru.kata.spring.boot_security.demo.model.Role;

public interface RoleService {
  public Set<Role> listRoles();


  public Role getRole(Long id);
  void saveRole(Role role);
  public void removeRole(Long id);
}
