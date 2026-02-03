package com.company.controllers.interfaces;

import com.company.models.User;

public interface IUserController {
    String getAllUsers();
    String getUser(int id);
    String deleteUser(int id);
    String createUser(String name, String surname, double balance, String login, String password, int role);
    String getUsersWithTransactions();
}
