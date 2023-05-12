package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "androot";
    private static final String URL = "jdbc:mysql://localhost:3306/moreusers";

    public static Connection getConnection() {

        // реализуйте настройку соеденения с БД
        Connection connection = null;

        {
            try {
              //  Driver driver = new com.mysql.cj.jdbc.Driver();
               //  DriverManager.registerDriver(driver);
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                System.out.println("Failed to load driver");
            }
        }

        return connection;
    }
}