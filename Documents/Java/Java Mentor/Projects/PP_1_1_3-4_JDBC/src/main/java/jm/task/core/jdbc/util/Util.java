package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

  // реализуйте настройку соеденения с БД
  private static final String URL = "jdbc:mysql://localhost:3306/user";
  private static final String USERNAME = "root";
  private static final String PASSWORD = "root";

  public static Connection getConnection() {

    Connection connection = null;
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return connection;
  }

  public static void close(Connection connection) {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}