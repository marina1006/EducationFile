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
  public void removeUser(long id) {
    dao.removeUser(id);
  }

  @Transactional
  public List<User> getListUsers() {

    return dao.listUsers();
  }

  @Override
  @Transactional
  public User getUser(long id) {
    return dao.getUser(id);
  }
}
