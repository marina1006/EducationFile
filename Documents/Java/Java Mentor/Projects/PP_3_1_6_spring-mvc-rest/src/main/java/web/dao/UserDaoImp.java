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

//    manager.merge(user);
    manager.persist(user);
  }

  @Override
  public void removeUser(Long id) {
   // manager.remove(id);
   manager.remove(manager.find(User.class, id));

  }

  @Override
  public User getUser(Long id) {

    return manager.find(User.class, id);
  }

  @Override
  public void update(Long id, User user) {

//    user.setName(user.getName());
//    user.setLastName(user.getLastName());
//    user.setEmail(user.getEmail());
   manager.merge(user);
  }

}
