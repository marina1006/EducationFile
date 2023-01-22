package ru.kata.spring.boot_security.demo.service;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

@Service
@Transactional(readOnly = true)
public class RoleService {

  @Autowired
  private final RoleDao roleDao;

  public RoleService(RoleDao roleDao) {
    this.roleDao = roleDao;
  }

  public Set<Role> listRoles() {

    return roleDao.listRoles().stream().collect(Collectors.toSet());
  }

  public Role getRole(Long id) {

    return roleDao.getRole(id);
  }
  public String getAuthority(Role role) {
    return role.getAuthority();
  }
  public void saveRole(Role role) {
    roleDao.saveRole(role);
  }

  public void removeRole(Long id) {
    roleDao.removeRole(id);
  }

  public String getRoleByName(String role) {
    return roleDao.getRoleByName(role).getAuthority();
  }
  public void update(Long id, Role role) {
    role.setId(id);
    roleDao.saveRole(role);
  }
}
