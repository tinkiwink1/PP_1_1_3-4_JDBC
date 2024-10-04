package jm.task.core.jdbc.util;

import java.sql.Connection;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
            conn = java.sql.DriverManager.getConnection(URL, USER, PASSWORD);
            if (!conn.isClosed()) {
                System.out.println("Connection established");
            }
        } catch (Exception e) {
            System.out.println("Connection is not available");
        }
        return conn;
    }
    // реализуйте настройку соеденения с БД
}
