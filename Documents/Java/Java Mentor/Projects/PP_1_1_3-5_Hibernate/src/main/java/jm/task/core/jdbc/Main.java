package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

public class Main {

  public static void main(String[] args) {
    // реализуйте алгоритм здесь
    // Util.getConnection();

    UserService userService = new UserServiceImpl();

    userService.createUsersTable();

    userService.saveUser("Ivan", "Ivanov", (byte) 20);
    userService.saveUser("Petr", "Petrov", (byte) 30);
    userService.saveUser("Anna", "Ivanova", (byte) 20);
    userService.saveUser("Maria", "Petrova", (byte) 30);

    userService.removeUserById(1);
    userService.getAllUsers();
    userService.cleanUsersTable();
    userService.dropUsersTable();
    Util.close();
  }
}
