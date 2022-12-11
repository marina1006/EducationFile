package jm.task.core.jdbc;

import java.sql.SQLException;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {

  public static void main(String[] args) throws SQLException {
    // реализуйте алгоритм здесь
    Util.getConnection();
    UserDao userDao = new UserDaoJDBCImpl();

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
