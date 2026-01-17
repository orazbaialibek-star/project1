package com.company.controllers;

import com.company.controllers.interfaces.ITransactionController;
import com.company.models.Transaction;
import com.company.models.User;
import com.company.repositories.interfaces.ITransactionRepository;
import com.company.repositories.interfaces.IUserRepository;

public class TransactionController implements ITransactionController {
    private final ITransactionRepository repo;

    public TransactionController(ITransactionRepository repo) { // Dependency Injection
        this.repo = repo;
    }

    public String userTransaction(int user1, int user2, int amount){
        Transaction trans = new Transaction(user1, user2, amount);

        boolean created = repo.userTransaction(trans);

        return (created ? "Transaction occured successfully" : "stfu");
//        Transaction trans = new Transaction(user1, user2, amount);
//        return (trans == null ? "User was not found!" : trans.toString());
    }
}
