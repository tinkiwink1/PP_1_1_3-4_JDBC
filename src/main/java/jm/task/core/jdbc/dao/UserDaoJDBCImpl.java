package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();
    PreparedStatement preparedStatement = null;
    Statement statement = null;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS Users(" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(256) NOT NULL, " +
                "lastname VARCHAR(256), " +
                "age TINYINT)";
        try(Connection connection = util.getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate(createTable);
            statement.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String dropTable = "DROP TABLE Users";
        try (Connection connection = util.getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate(dropTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insert_user = "INSERT INTO Users (name, lastname, age) VALUES (?, ?, ?)";
        try (Connection connection = util.getConnection()) {
            preparedStatement = connection.prepareStatement(insert_user);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String deleteUser = "DELETE FROM Users WHERE id = ?";
        try (Connection connection = util.getConnection()) {
            preparedStatement = connection.prepareStatement(deleteUser);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String selectAll = "SELECT * FROM Users";
        List<User> users = new ArrayList<>();
        try (Connection connection = util.getConnection()) {
            preparedStatement = connection.prepareStatement(selectAll);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastname"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }
            preparedStatement.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return users;
    }

    public void cleanUsersTable() {
        String cleanTable = "TRUNCATE TABLE Users";
        try (Connection connection = util.getConnection()) {
            preparedStatement = connection.prepareStatement(cleanTable);
            statement = connection.createStatement();
            statement.executeUpdate(cleanTable);
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
