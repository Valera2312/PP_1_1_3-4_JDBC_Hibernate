package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

          UserServiceImpl userService = new UserServiceImpl();
          userService.dropUsersTable();
          userService.createUsersTable();
          userService.saveUser("test","test",(byte)10);
          userService.removeUserById(1);
          userService.getAllUsers().forEach(e -> System.out.println(e.toString()));
          userService.cleanUsersTable();

        //HibernateUtil.getSessionFactory();
    }
}
