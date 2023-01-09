package ru.kata.spring.boot_security.demo.service;

import java.util.List;
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

  public List<Role> listRole() {

    return roleDao.listRoles();

  }

  public Role getRole(Long id) {
    return roleDao.getRole(id);
  }


}
