package jm.task.core.jdbc.dao;


import java.util.ArrayList;
import jm.task.core.jdbc.model.User;

import java.util.List;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import org.hibernate.query.Query;

public class UserDaoHibernateImpl implements UserDao {

  public UserDaoHibernateImpl() {

  }

  @Override
  public void createUsersTable() {

    try (Session session = Util.buildSessionFactory().openSession()) {

      session.beginTransaction();
      Query query = session.createNativeQuery("CREATE TABLE IF NOT EXISTS users (" +
          "(id INT PRIMARY KEY AUTO_INCREMENT, " +
          "name VARCHAR(100) NOT NULL, " +
          "lastName VARCHAR(100) NOT NULL, " +
          "age SMALLINT NOT NULL)");
      query.executeUpdate();
      session.getTransaction().commit(); // закрываем
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void dropUsersTable() {

    try (Session session = Util.buildSessionFactory().openSession()) {

      session.beginTransaction();
      Query query = session.createNativeQuery("DROP TABLE IF EXISTS users");
      query.executeUpdate();
      cleanUsersTable();
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void saveUser(String name, String lastName, byte age) {
    Session session = Util.buildSessionFactory().openSession();
    try {

      session.beginTransaction();
      session.persist(new User(name, lastName, age));

      session.getTransaction().commit();
      System.out.println("User с именем – " + name + " добавлен в базу данных");
    } catch (Exception e) {
      if (session.getTransaction() != null) {
        session.getTransaction().rollback();
      }
      e.printStackTrace();
    }
    finally {
      session.close();
    }
  }

  @Override
  public void removeUserById(long id) {

    try (Session session = Util.buildSessionFactory().openSession()) {

      session.beginTransaction();

      User user = session.get(User.class, id); // select
      session.delete(user);
      session.getTransaction().commit();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<User> getAllUsers() {

    Session session = Util.buildSessionFactory().openSession();
    List<User> users = new ArrayList<>();
    try  {
      session.beginTransaction();

      users = session.createQuery("FROM User", User.class).getResultList();

      session.getTransaction().commit();
      users.forEach(System.out::println);
    } catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      session.close();
    }
    return users;
  }

  @Override
  public void cleanUsersTable() {

    try (Session session = Util.buildSessionFactory().openSession();) {

      session.beginTransaction();
      Query query = session.createQuery("DELETE FROM User");
      query.executeUpdate();
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
