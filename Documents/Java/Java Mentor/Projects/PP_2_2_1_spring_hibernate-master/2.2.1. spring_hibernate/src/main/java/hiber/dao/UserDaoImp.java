package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void add(Car car) {
   sessionFactory.getCurrentSession().save(car);
   }

   @Override
   public List<User> getlistCar(String model, int series) {
      TypedQuery query = sessionFactory.getCurrentSession().createQuery("from User user join fetch user.car where user.car.model = :model and user.car.series = :series");
      query.setParameter("model", model);
      query.setParameter("series", series);
//      TypedQuery<User> resultQuery=sessionFactory.getCurrentSession().
//          createQuery("from User where car = :car");
//      query.setParameter("car", query.getSingleResult());
      return (List<User>) query.getSingleResult();
   }


}
