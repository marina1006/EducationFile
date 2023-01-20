package ru.kata.spring.boot_security.demo.service;

import java.util.List;
import ru.kata.spring.boot_security.demo.model.Role;

public interface RoleService {
  public List<Role> listRole();


  public Role getRole(Long id);
  void saveRole(Role role);
  public List<Role> listRoleId(Long id);
}
