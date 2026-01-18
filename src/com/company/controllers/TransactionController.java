package com.company.controllers;

import com.company.controllers.interfaces.ITransactionController;
import com.company.models.Transaction;
import com.company.models.User;
import com.company.repositories.interfaces.ITransactionRepository;
import com.company.repositories.interfaces.IUserRepository;

import java.util.List;

public class TransactionController implements ITransactionController {
    private final ITransactionRepository repo;

    public String getAllTransactions() {
        List<Transaction> transes = repo.getAllTransactions();

        StringBuilder response = new StringBuilder();
        for (Transaction trans : transes) {
            response.append(trans.toString()).append("\n");
        }

        return response.toString();
    }

    public TransactionController(ITransactionRepository repo) { // Dependency Injection
        this.repo = repo;
    }

    public String userTransaction(int user1, int user2, int amount){
        Transaction trans = new Transaction(user1, user2, amount);

        boolean created = repo.userTransaction(trans);

        return (created ? "Transaction occured successfully" : "Transaction failed");
    }
}
