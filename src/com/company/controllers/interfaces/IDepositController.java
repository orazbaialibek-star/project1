package com.company.controllers.interfaces;

import com.company.models.Deposit;

public interface IDepositController {
    String getAllMyDeposits(int id);
    String createDeposit(int userid, double percentage, int balance);
}
