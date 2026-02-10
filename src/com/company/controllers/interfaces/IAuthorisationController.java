package com.company.controllers.interfaces;

public interface IAuthorisationController {
    String authorisation(String login, String password);
    int getRole(String login);
    String getFullName(String login);
    int getId(String login);
}
