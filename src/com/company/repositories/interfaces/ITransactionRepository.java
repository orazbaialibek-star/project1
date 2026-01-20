package com.company.repositories.interfaces;

import com.company.models.Transaction;

import java.util.List;

public interface ITransactionRepository {
    boolean userTransaction(Transaction trans);
    List<Transaction> getAllTransactions();
}
