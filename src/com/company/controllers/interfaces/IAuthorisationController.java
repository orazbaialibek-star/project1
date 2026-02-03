package com.company.controllers.interfaces;

public interface IAuthorisationController {
    String checkLogPas(String login, String password);
    int checkRole(String login);
    String checkLogin(String login);
    public int checkId(String login);
}
