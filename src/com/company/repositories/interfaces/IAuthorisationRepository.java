package com.company.repositories.interfaces;

public interface IAuthorisationRepository {
    boolean checkLogPas(String login, String password);
    int checkRole(String login);
    String checkLogin(String login);
    int checkId(String login);
}
