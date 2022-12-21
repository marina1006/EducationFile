package web.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

@Repository
public class UserDaoImp implements UserDao {

  @PersistenceContext
  private EntityManager manager;

  public List<User> listUsers() {

    TypedQuery<User> query = manager.createQuery("from User", User.class);
    return query.getResultList();
  }

  public void saveUser(User user) {
    manager.merge(user);
  }

  public void removeUser(long id) {
    manager.remove(id);
  }

  @Override
  public User getUser(long id) {

    return manager.find(User.class, id);
  }

}
