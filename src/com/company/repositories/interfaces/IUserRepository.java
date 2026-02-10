package com.company.repositories.interfaces;

import com.company.models.User;

import java.util.List;

public interface IUserRepository {
    List<User> getAllUsers();
    User getUser(int id);
    boolean deleteUser(int id);
    boolean createUser(User user);
    List<String> getUsersWithTransactions();
    int checkBalance(int id);
    int getRole(String login);
    String getFullName(String login);
    int getId(String login);
}
