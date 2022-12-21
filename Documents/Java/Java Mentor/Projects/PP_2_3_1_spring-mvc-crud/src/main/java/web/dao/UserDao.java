package web.dao;

import java.util.List;
import web.model.User;

public interface UserDao {

   List<User> listUsers();

   void saveUser(User user);

   void removeUser(long id);

  public User getUser(long id);
  public void update(long id, User user);
}
