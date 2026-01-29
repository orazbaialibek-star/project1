package com.company.controllers;

import com.company.controllers.interfaces.IAuthorisationController;
import com.company.repositories.interfaces.IAuthorisationRepository;

public class AuthorisationController implements IAuthorisationController {
    private final IAuthorisationRepository repo;

    public AuthorisationController(IAuthorisationRepository repo) { // Dependency Injection
        this.repo = repo;
    }

    public String checkLogPas_Admin(String login, String password){
        boolean authorised = repo.checkLogPas_Admin(login, password);

        return (authorised ? "Logged in succssfully!" : null);
    }
}
