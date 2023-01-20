package ru.kata.spring.boot_security.demo.dao;

import java.util.List;
import ru.kata.spring.boot_security.demo.model.Role;

public interface RoleDao {
  Role getRole(Long id);
  List<Role> listRoles();

  void saveRole(Role role);
  List<Role> listRoleId(Long id);

  void removeRole(Long id);

  void update(Role role,Long id);
}
