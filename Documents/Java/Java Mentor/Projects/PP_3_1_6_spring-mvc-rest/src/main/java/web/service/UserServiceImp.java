package web.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

@Service
public class UserServiceImp implements UserService {

  @Autowired
  private UserDao dao;

  @Override
  @Transactional
  public void saveUser(User user) {
    dao.saveUser(user);
  }

  @Override
  @Transactional
  public List<User> removeUser(Long id) {
    dao.removeUser(id);
    return null;
  }

  @Override
  @Transactional
  public List<User> getListUsers() {
    return dao.listUsers();
  }

  @Override
  @Transactional
  public User getUser(Long id) {
    return dao.getUser(id);
  }

  @Override
  @Transactional
  public List<User> update(Long id, User user) {
    dao.update(id, user);
    return null;
  }

}
