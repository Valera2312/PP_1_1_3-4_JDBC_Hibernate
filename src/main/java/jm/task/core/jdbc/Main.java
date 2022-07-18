package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

          UserServiceImpl userService = new UserServiceImpl();
          userService.saveUser("test","test", (byte)26);
          //userService.removeUserById(9);
          userService.getAllUsers().forEach(e -> System.out.println(e.toString()));
          userService.cleanUsersTable();

        //HibernateUtil.getSessionFactory();
    }
}
