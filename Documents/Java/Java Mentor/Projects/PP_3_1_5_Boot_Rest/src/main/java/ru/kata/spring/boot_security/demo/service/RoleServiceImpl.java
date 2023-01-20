package ru.kata.spring.boot_security.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

  @Autowired
  private final RoleDao roleDao;

  public RoleServiceImpl(RoleDao roleDao) {
    this.roleDao = roleDao;
  }

  public List<Role> listRole() {

    return roleDao.listRoles();

  }

  public Role getRole(Long id) {
    return roleDao.getRole(id);
  }
  public void saveRole(Role role) {

  roleDao.saveRole(role);
  }
  public List<Role> listRoleId(Long id) {

    return roleDao.listRoles();

  }


}
