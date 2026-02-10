package com.company.repositories.interfaces;

import com.company.models.Deposit;
import com.company.models.Transaction;

import java.util.List;

public interface IDepositRepository {
    boolean createDeposit(Deposit deposit);
    boolean replenishDeposit(int id, int userid, int amount);
    boolean withdrawDeposit(int id, int userid, int amount);
    List<Deposit> getAllMyDeposits(int id);
    List<Deposit> getAllDeposits();
    //int checkBalanceUser(int userid);
    int checkBalanceDeposit(int id);
}
