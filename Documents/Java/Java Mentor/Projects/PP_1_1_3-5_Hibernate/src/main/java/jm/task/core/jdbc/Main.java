package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.util.Util;

public class Main {

  public static void main(String[] args) {
    // реализуйте алгоритм здесь
    // Util.getConnection();
    Util.getSessionFactory();
    //UserDao userDao = new UserDaoJDBCImpl();
    UserDao userDao = new UserDaoHibernateImpl();

    userDao.createUsersTable();

    userDao.saveUser("Ivan", "Ivanov", (byte) 20);
    userDao.saveUser("Petr", "Petrov", (byte) 30);
    userDao.saveUser("Anna", "Ivanova", (byte) 20);
    userDao.saveUser("Maria", "Petrova", (byte) 30);

    userDao.removeUserById(1);
    userDao.getAllUsers();
    userDao.cleanUsersTable();
    userDao.dropUsersTable();

  }
}
