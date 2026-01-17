package com.company.repositories.interfaces;

import com.company.models.Transaction;
import com.company.models.User;

public interface ITransactionRepository {
    boolean userTransaction(Transaction trans);
}
