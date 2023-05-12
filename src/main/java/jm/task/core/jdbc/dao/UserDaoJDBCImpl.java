package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS  users ( id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(255), last_name VARCHAR(255), age INT )");
            System.out.println("The creation of the table was successful");
        } catch (SQLException e) {
            System.out.println("Error creating the table");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            System.out.println("Successful table deletion");
        } catch (SQLException e) {
            System.out.println("Error when deleting a table");
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement save = connection.prepareStatement("INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)")) {
            save.setString(1, name);
            save.setString(2, lastName);
            save.setInt(3, age);
            save.executeUpdate();
            System.out.println("User c именем:  " + name + " добавлен в таблицу");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("Remove successful");
        } catch (SQLException e) {
            System.out.println("Remove not successful");
        }
    }


    public List < User > getAllUsers() {
        List < User > users = new ArrayList < > ();
        try (PreparedStatement statement = connection.prepareStatement(" SELECT id, name, last_name, age FROM users " );) {
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                User x = new User();
                x.setId(res.getLong("id"));
                x.setName(res.getString("name"));
                x.setLastName(res.getString("last_name"));
                x.setAge(res.getByte("age"));
                users.add(x);
                System.out.println("массив заполнен");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }


    public void cleanUsersTable() {
        try (PreparedStatement statement = connection.prepareStatement("TRUNCATE TABLE users")) {
            statement.executeUpdate();
            System.out.println("Clear");
        } catch (SQLException e) {
            System.out.println("Not clear");

        }

    }
}