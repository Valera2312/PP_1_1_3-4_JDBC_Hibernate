package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getMySQLConnection() {
        String hostName = "localhost";
        String dbName = "spring_db";
        String userName = "root";
        String password = "root";
        return getMySQLConnection(hostName, dbName, userName, password);
    }
    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

            return DriverManager.getConnection(connectionURL, userName,
                    password);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("database connection error");
            return null;
        }

    }
}
