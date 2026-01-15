package com.company.repositories.interfaces;

import com.company.models.User;

import java.util.List;

public interface IUserRepository {
    List<User> getAllUsers();
}
