package com.company.controllers.interfaces;

import com.company.models.User;

public interface IAuthorisationController {
    String authorisation(String login, String password);
    String createNewAcc(String name, String surname, String login, String password, int role);
    int getRole(String login);
    String getFullName(String login);
    int getId(String login);
}
