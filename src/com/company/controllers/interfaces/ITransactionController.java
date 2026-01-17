package com.company.controllers.interfaces;

import com.company.models.User;

public interface ITransactionController {
    String getAllTransactions();
    String userTransaction(int user1, int user2, int amount);
}
