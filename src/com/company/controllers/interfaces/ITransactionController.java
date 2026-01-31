package com.company.controllers.interfaces;

public interface ITransactionController {
    String getAllTransactions();
    String userTransaction_admin(int user1, int user2, int amount);
}
