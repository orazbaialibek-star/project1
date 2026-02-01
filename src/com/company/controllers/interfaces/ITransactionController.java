package com.company.controllers.interfaces;

public interface ITransactionController {
    String getAllTransactions_admin();
    String getAllTransactions_user(int id);
    String userTransaction_admin(int user1, int user2, int amount);
}
