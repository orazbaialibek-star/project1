package com.company.controllers;

import com.company.controllers.interfaces.ITransactionController;
import com.company.models.Transaction;
import com.company.repositories.interfaces.ITransactionRepository;


import java.util.List;

public class TransactionController implements ITransactionController {
    private final ITransactionRepository repo;
    public TransactionController(ITransactionRepository repo) { // Dependency Injection
        this.repo = repo;
    }

    public String getAllTransactions_admin() {
        List<Transaction> transes = repo.getAllTransactions_admin();

        StringBuilder response = new StringBuilder();
        for (Transaction trans : transes) {
            response.append(trans.toString()).append("\n");
        }

        return response.toString();
    }

    public String getAllTransactions_user(int id) {
        List<Transaction> transes = repo.getAllTransactions_user(id);

        StringBuilder response = new StringBuilder();
        for (Transaction trans : transes) {
            response.append(trans.toString()).append("\n");
        }

        return response.toString();
    }

    public String userTransaction(int user1, int user2, int amount){
        Transaction trans = new Transaction(user1, user2, amount);

        if (user1 == user2){
            return "Cannot transfer money to yourself";
        }
        if (amount <= 0){
            return "Amount cannot be less or equal to 0";
        }

        int balance = repo.checkBalance(user1);

        if (amount > balance){
            return "Insufficient funds";
        }

        boolean created = repo.userTransaction(trans);

        return (created ? "Transaction occured successfully" : "Transaction failed");
    }
}
