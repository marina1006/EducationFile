package jm.task.core.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {

  public static void main(String[] args) throws SQLException {
    // реализуйте алгоритм здесь
    Connection connection = Util.getConnection();
    UserService service = new UserServiceImpl();

    service.createUsersTable();

    service.saveUser("Ivan", "Ivanov", (byte) 20);
    service.saveUser("Petr", "Petrov", (byte) 30);
    service.saveUser("Anna", "Ivanova", (byte) 20);
    service.saveUser("Maria", "Petrova", (byte) 30);

    service.removeUserById(1);
    service.getAllUsers();
    service.cleanUsersTable();
    service.dropUsersTable();
    Util.close(connection);
  }
}
