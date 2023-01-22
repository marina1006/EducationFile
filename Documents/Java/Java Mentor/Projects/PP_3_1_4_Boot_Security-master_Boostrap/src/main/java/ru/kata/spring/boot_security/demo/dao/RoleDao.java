package ru.kata.spring.boot_security.demo.dao;

import java.util.Set;
import ru.kata.spring.boot_security.demo.model.Role;

public interface RoleDao {
  Role getRole(Long id);
  Set<Role> listRoles();

  void saveRole(Role role);

  void removeRole(Long id);
  Role getRoleByName(String name);
}
