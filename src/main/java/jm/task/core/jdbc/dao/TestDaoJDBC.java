package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.util.List;

public class TestDaoJDBC {
    public static void test() {
        UserServiceImpl userService = new UserServiceImpl();
        Util util = new Util();
        List<User> listUsers = null;
        try (Connection conn = util.getConnection()) {
            conn.setAutoCommit(false);
            // создание таблицы
            userService.createUsersTable();
            // заполняем таблицу данными
            userService.saveUser("il", "gim", (byte) 19);
            userService.saveUser("rus", "sid", (byte) 20);
            userService.saveUser("dan", "dan", (byte) 21);
            conn.commit();
            // получение и вывод данных в консоль
            userService.getAllUsers().forEach(System.out::println);
            // удаление пользователя по его id в таблице
            userService.removeUserById(1);
            conn.commit();
            userService.getAllUsers().forEach(System.out::println);
            // очитска таблицы
            userService.cleanUsersTable();
            conn.commit();
            // удаление таблицы
            userService.dropUsersTable();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
