package jm.task.core.jdbc.dao;

import com.sun.jdi.connect.Connector;
import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getMySQLConnection;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection connector = getMySQLConnection();


    public UserDaoJDBCImpl() {

    }


    public void createUsersTable() {
        String SQL = "create table if not exists users " +
                "(id  bigint auto_increment primary key, " +
                "name     varchar(30)  null," +
                "lastName varchar(30)  null," +
                "age      tinyint      null)";
        try {
            Statement statement = connector.createStatement();
            statement.executeUpdate(SQL);
            System.out.println("Table was successfully created");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        String SQL = "drop table if exists users";
        try {
            Statement statement = connector.createStatement();
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL = "insert into users (name, lastName, age) values (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connector.prepareStatement(SQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println(name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String SQL = "delete from users where id = (?)";
        try {
            PreparedStatement preparedStatement = connector.prepareStatement(SQL);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String SQL = "select * from users";
        try {
            Statement statement = connector.createStatement();
            ResultSet set = statement.executeQuery(SQL);
            int i = 1;
            while (set.next()) {
                User user = new User();
                user.setId(set.getLong(i++));
                user.setName(set.getString(i++));
                user.setLastName(set.getString(i++));
                user.setAge(set.getByte(i));
                users.add(user);
                i = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return users;
    }

    public void cleanUsersTable() {
        String SQL = "TRUNCATE users";
        try {
            Statement statement = connector.createStatement();
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
