package com.company.repositories.interfaces;

public interface IAuthorisationRepository {
    boolean checkLogPas_Admin(String login, String password);
    int getRole();
    int checkRole(String login);
}
