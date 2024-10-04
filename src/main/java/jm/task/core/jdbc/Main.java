package jm.task.core.jdbc;

import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Util util = new Util();
        Statement statement;
        ResultSet resultSet;
        String query;

        try (Connection conn = util.getConnection()) {
            conn.setAutoCommit(false);
            //создание таблицы
            statement = conn.createStatement();
            query = "CREATE TABLE IF NOT EXISTS Users(" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(256) NOT NULL, " +
                    "lastname VARCHAR(256), " +
                    "age TINYINT)";
            System.out.println("tables Users created");

            statement.executeUpdate(query);
            //добавление юзера №1 в таблицу
            query = "INSERT INTO Users(name, lastname, age) VALUES ('Ildar', 'Gimadutdinov', 19)";
            statement.executeUpdate(query);
            System.out.println("added users 1");

            //добавление юзера №2 в таблицу
            query = "INSERT INTO Users(name, lastname, age) VALUES ('Ruslan', 'Sidorov', 24)";
            statement.executeUpdate(query);
            System.out.println("added users 2");
            //получение данных из таблицы
            query = "SELECT * FROM Users";
            resultSet = statement.executeQuery(query);
            System.out.println("table users");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id")+ " " + resultSet.getString("name")
                        + " " + resultSet.getString("lastname")
                        + " " + resultSet.getInt("age"));
            }
            resultSet.close();
            //удаление юзера из таблицы
            query = "DELETE FROM USERS WHERE id = 1";
            statement.executeUpdate(query);
            System.out.println("deleted users 1");
            //очистка таблицы
            query = "TRUNCATE TABLE USERS";
            statement.executeUpdate(query);
            System.out.println("truncated users");
            //удаление таблицы
            query = "DROP TABLE USERS";
            statement.executeUpdate(query);
            System.out.println("dropped users");
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("closing connection");
        }
    }
}
