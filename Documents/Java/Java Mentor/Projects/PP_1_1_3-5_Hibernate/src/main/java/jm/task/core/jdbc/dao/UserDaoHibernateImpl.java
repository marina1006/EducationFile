package jm.task.core.jdbc.dao;


import java.util.ArrayList;
import jm.task.core.jdbc.model.User;

import java.util.List;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class UserDaoHibernateImpl implements UserDao {
  private SessionFactory sessionFactory = Util.getSessionFactory();

  public UserDaoHibernateImpl() {

  }


  @Override
  public void createUsersTable() {
    Session session = Util.getSessionFactory().openSession();
    try {


      session.beginTransaction();
      Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS User (" +
          "(id INT PRIMARY KEY AUTO_INCREMENT, " +
          "name VARCHAR(100) NOT NULL, " +
          "lastName VARCHAR(100) NOT NULL, " +
          "age SMALLINT NOT NULL)");
      query.executeUpdate();
      session.getTransaction().commit(); // закрываем
    } catch (Exception e) {
      if(session.getTransaction() != null) {
        session.getTransaction().rollback();
      }
    } finally {
      Util.getSessionFactory().close();
    }
  }

  @Override
  public void dropUsersTable() {
    Session session = Util.getSessionFactory().openSession();
    try {

      session.beginTransaction();
      Query query = session.createSQLQuery("DROP TABLE IF EXISTS User");
      query.executeUpdate();
      cleanUsersTable();
      session.getTransaction().commit();
    } catch (Exception e) {
      if(session.getTransaction() != null) {
        session.getTransaction().rollback();
      }
    } finally {
      Util.getSessionFactory().close();
    }
  }

  @Override
  public void saveUser(String name, String lastName, byte age) {
    try (Session session = Util.getSessionFactory().openSession()) {
      try {
        session.beginTransaction();

        User user = new User(name, lastName, age);
        session.save(user);
        session.getTransaction().commit();
        //System.out.println("User с именем – " + name + " добавлен в базу данных");
      } catch (Exception e) {
        if (session.getTransaction() != null) {
          session.getTransaction().rollback();
        }
      } finally {
        Util.getSessionFactory().close();
      }
    }
  }

  @Override
  public void removeUserById(long id) {
    Session session = Util.getSessionFactory().openSession();
    try {

      session.beginTransaction();

      User user = session.get(User.class, id); // select
      session.delete(user);
      session.getTransaction().commit();

    } catch (Exception e) {
      if(session.getTransaction() != null) {
        session.getTransaction().rollback();
      }
    } finally {
      Util.getSessionFactory().close();
    }
  }

  @Override
  public List<User> getAllUsers() {
    List<User> users = new ArrayList<>();
    Session session = Util.getSessionFactory().openSession();
    try {
      session.beginTransaction();

      users = session.createSQLQuery("FROM User").list();

      session.getTransaction().commit();
      users.forEach(System.out::println);
    } catch (Exception e) {
      if (session.getTransaction() != null) {
        e.printStackTrace();
        session.getTransaction().rollback();
      }
    } finally {
      Util.getSessionFactory().close();
    }
    return users;
  }

  @Override
  public void cleanUsersTable() {
    Session session = Util.getSessionFactory().openSession();
    try {

      session.beginTransaction();
      Query query = session.createQuery("DELETE FROM User");
      query.executeUpdate();
      session.getTransaction().commit();
    } catch (Exception e) {
      if(session.getTransaction() != null) {
        session.getTransaction().rollback();
      }
    } finally {
      Util.getSessionFactory().close();
    }
  }
}
