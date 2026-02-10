package com.company.controllers;

import com.company.controllers.interfaces.IAuthorisationController;
import com.company.repositories.interfaces.IAuthorisationRepository;
import com.company.repositories.interfaces.IUserRepository;

public class AuthorisationController implements IAuthorisationController {
    private final IAuthorisationRepository repo;
    private final IUserRepository repoU;

    public AuthorisationController(IAuthorisationRepository repo, IUserRepository repoU) { // Dependency Injection
        this.repo = repo;
        this.repoU = repoU;
    }

    public String authorisation(String login, String password){
        boolean authorised = repo.authorisation(login, password);
        String log = repoU.getFullName(login);
        int role = repoU.getRole(login);
        String role_text = "NONDEFINED";
        if(role == 1){
            role_text = "admin";
        } else if (role == 2) {
            role_text = "user";
        }

        return (authorised ? "Logged in succssfully as " + log + " (" + role_text + ")" : null);
    }

    public int getRole(String login){
        int role = repoU.getRole(login);

        return role;
    }
    
    public String getFullName(String login){
        String fullName = repoU.getFullName(login);
        
        return fullName;
    }

    public int getId(String login){
        int id = repoU.getId(login);

        return id;
    }
}
