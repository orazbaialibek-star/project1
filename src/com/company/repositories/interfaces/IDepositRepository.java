package com.company.repositories.interfaces;

import com.company.models.Deposit;
import com.company.models.Transaction;

import java.util.List;

public interface IDepositRepository {
    boolean createDeposit(Deposit deposit);
//    boolean withdrawDeposit(int id);
    List<Deposit> getAllMyDeposits(int id);
}
