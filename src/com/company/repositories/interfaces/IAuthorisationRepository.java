package com.company.repositories.interfaces;

import com.company.models.User;

public interface IAuthorisationRepository {
    boolean authorisation(String login, String password);
}
