//package jm.task.core.jdbc.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import jm.task.core.jdbc.model.User;
//
//import java.util.List;
//import jm.task.core.jdbc.util.Util;
//
//public class UserDaoJDBCImpl implements UserDao {
//
//  private static Connection connection = Util.getConnection();
//
//  public UserDaoJDBCImpl() {
//
//  }
//
//  public void createUsersTable() {
//    try {
//      Statement statement = connection.createStatement();
//      statement.execute("CREATE TABLE IF NOT EXISTS user (" +
//          "id INT PRIMARY KEY AUTO_INCREMENT, " +
//          "name VARCHAR(100) NOT NULL, " +
//          "lastName VARCHAR(100) NOT NULL, " +
//          "age SMALLINT NOT NULL)");
//    } catch (SQLException e) {
//      e.printStackTrace();
//      connection.rollback();
//    }
//  }
//
//  public void dropUsersTable() {
//
//    try {
//      Statement statement = connection.createStatement();
//      statement.execute("DROP TABLE IF EXISTS user");
//    } catch (SQLException e) {
//      e.printStackTrace();
//      connection.rollback();
//    }
//  }
//
//
//  public void saveUser(String name, String lastName, byte age) {
//    try {
//      PreparedStatement statement = connection.prepareStatement(
//          "INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)");
//      statement.setString(1, name);
//      statement.setString(2, lastName);
//      statement.setByte(3, age);
//      statement.executeUpdate();
//      System.out.println("User с именем – " + name + " добавлен в базу данных");
//    } catch (SQLException e) {
//      e.printStackTrace();
//      connection.rollback();
//    }
//  }
//
//  public void removeUserById(long id) {
//    try {
//      PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE Id = ?");
//      statement.setLong(1, id);
//      statement.executeUpdate();
//    } catch (SQLException e) {
//      e.printStackTrace();
//      connection.rollback();
//    }
//  }
//
//  public List<User> getAllUsers() {
//    List<User> users = new ArrayList<>();
//
//    try {
//      Statement statement = connection.createStatement();
//      ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
//      while (resultSet.next()) {
//        User user = new User();
//        user.setId(resultSet.getLong("id"));
//        user.setName(resultSet.getString("name"));
//        user.setLastName(resultSet.getString("lastName"));
//        user.setAge(resultSet.getByte("age"));
//        users.add(user);
//      }
//    } catch (SQLException e) {
//      e.printStackTrace();
//      connection.rollback();
//    }
//    return users;
//  }
//
//  public void cleanUsersTable() {
//
//    try (Statement statement = connection.createStatement()) {
//      statement.executeUpdate("DELETE FROM user");
//    } catch (SQLException e) {
//      e.printStackTrace();
//      connection.rollback();
//    }
//  }
//}
//
