package jm.task.core.jdbc.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import jm.task.core.jdbc.model.User;

import java.util.List;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.query.Query;

public class UserDaoHibernateImpl implements UserDao {

  private static final SessionFactory sessionFactory = Util.buildSessionFactory();
  private Transaction transaction;

  public UserDaoHibernateImpl() {

  }

  @Override
  public void createUsersTable()   {

    try (Session session = sessionFactory.openSession()) {

      transaction = session.beginTransaction();
      Query query = session.createNativeQuery("CREATE TABLE IF NOT EXISTS users (" +
          "id INT PRIMARY KEY AUTO_INCREMENT, " +
          "name VARCHAR(100) NOT NULL, " +
          "lastName VARCHAR(100) NOT NULL, " +
          "age SMALLINT NOT NULL)");
      query.executeUpdate();
      transaction.commit(); // закрываем
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
  }

  @Override
  public void dropUsersTable() {

    try (Session session = sessionFactory.openSession()) {

      session.beginTransaction();
      Query query = session.createNativeQuery("DROP TABLE IF EXISTS users");
      query.executeUpdate();
      cleanUsersTable();
      session.getTransaction().rollback();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
  }

  @Override
  public void saveUser(String name, String lastName, byte age) {

    try (Session session = sessionFactory.openSession()) {
      session.beginTransaction();
      session.persist(new User(name, lastName, age));

      session.getTransaction().commit();
      System.out.println("User с именем – " + name + " добавлен в базу данных");
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }

  }

  @Override
  public void removeUserById(long id) {

    try (Session session = sessionFactory.openSession()) {

      session.beginTransaction();

      User user = session.get(User.class, id); // select
      session.delete(user);
      session.getTransaction().commit();
    } catch (TransactionException e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
  }

  @Override
  public List<User> getAllUsers() {

    List<User> users = new ArrayList<>();
    try (Session session = sessionFactory.openSession()) {
      session.beginTransaction();

      users = session.createQuery("FROM User", User.class).getResultList();

      session.getTransaction().commit();
      users.forEach(System.out::println);
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
    return users;
  }

  @Override
  public void cleanUsersTable() {

    try (Session session = sessionFactory.openSession();) {

      transaction = session.beginTransaction();
      Query query = session.createNativeQuery("DELETE FROM User");
      query.executeUpdate();
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
  }
}
