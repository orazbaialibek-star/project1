package com.company.controllers.interfaces;

public interface IAuthorisationController {
    String checkLogPas_Admin(String login, String password);
    int checkRole(String login);
}
