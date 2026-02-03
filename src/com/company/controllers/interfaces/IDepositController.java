package com.company.controllers.interfaces;

import com.company.models.Deposit;

public interface IDepositController {
    String getAllMyDeposits(int id);
    String getAllDeposits();
    String createDeposit(int userid, double percentage, int balance);
    String replenishDeposit(int id, int userid, int amount);
    String withdrawDeposit(int id, int userid, int amount);
}
