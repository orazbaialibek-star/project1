package com.company.controllers;

import com.company.controllers.interfaces.IAuthorisationController;
import com.company.repositories.interfaces.IAuthorisationRepository;

public class AuthorisationController implements IAuthorisationController {
    private final IAuthorisationRepository repo;

    public AuthorisationController(IAuthorisationRepository repo) { // Dependency Injection
        this.repo = repo;
    }

    public String checkLogPas(String login, String password){
        boolean authorised = repo.checkLogPas(login, password);
        String log = repo.checkLogin(login);
        int role = repo.checkRole(login);
        String role_text = "NONDEFINED";
        if(role == 1){
            role_text = "admin";
        } else if (role == 2) {
            role_text = "user";
        }

        return (authorised ? "Logged in succssfully as " + login + " (" + role_text + ")" : null);
    }

    public int checkRole(String login){
        int role = repo.checkRole(login);

        return role;
    }
    
    public String checkLogin(String login){
        String log = repo.checkLogin(login);
        
        return log;
    }

    public int checkId(String login){
        int id = repo.checkId(login);

        return id;
    }
}
