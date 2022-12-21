package web.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import web.model.User;

@Repository
public class UserDaoImp implements UserDao {

  @PersistenceContext
  private EntityManager manager;

  public List<User> listUsers() {

    TypedQuery<User> query = manager.createQuery("from User", User.class);
    return query.getResultList();
  }
  @Override
  public void saveUser(User user) {
    manager.merge(user);
  }
  @Override
  public void removeUser(long id) {
    manager.remove(id);
  }

  @Override
  public User getUser(long id) {

    return manager.find(User.class, id);
  }
  @Override
  public void update(long id, User user) {
    user.setName(user.getName());
    user.setSurname(user.getSurname());
    user.setEmail(user.getEmail());
  }

}
