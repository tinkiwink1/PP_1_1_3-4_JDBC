package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDaoJDBC {
    private static final UserServiceImpl userService = new UserServiceImpl();
    private static void doJob1() {
        // создание таблицы
        userService.createUsersTable();
        // заполняем таблицу данными
        userService.saveUser("il", "gim", (byte) 19);
        userService.saveUser("rus", "sid", (byte) 20);
        userService.saveUser("dan", "dan", (byte) 21);
        // получение и вывод данных в консоль
        userService.getAllUsers().forEach(System.out::println);
    }
    private static void doJob2() {
        // удаление пользователя по его id в таблице
        userService.removeUserById(1);
        userService.getAllUsers().forEach(System.out::println);
    }
    private static void doJob3() {
        // очитска таблицы
        userService.cleanUsersTable();
    }
    private static void doJob4() {
        // удаление таблицы
        userService.dropUsersTable();
    }
    public static void main(String[] args) throws SQLException {
        Util util = new Util();
        Connection conn = null;
        try {
            conn = util.getConnection();
            conn.setAutoCommit(false);
            doJob1();
            conn.commit();
            System.out.println("Done");
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
            System.out.println("Rollback");
        }
        try {
            conn = util.getConnection();
            conn.setAutoCommit(false);
            doJob2();
            conn.commit();
            System.out.println("Done");
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
            System.out.println("Rollback");
        }
        try {
            conn = util.getConnection();
            conn.setAutoCommit(false);
            doJob3();
            conn.commit();
            System.out.println("Done");
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
            System.out.println("Rollback");
        }
        try {
            conn = util.getConnection();
            conn.setAutoCommit(false);
            doJob4();
            conn.commit();
            System.out.println("Done");
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
            System.out.println("Rollback");
        }
    }
}
