
package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Alexander", "Alex", (byte) 25);
        userService.saveUser("Mariya", "Mary", (byte) 28);
        userService.saveUser("Maxim", "Max", (byte) 23);
        userService.saveUser("Vladislav", "Vlad", (byte) 30);
        userService.getAllUsers();
        userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}