package com.company.controllers;

import com.company.controllers.interfaces.IDepositController;
import com.company.models.Deposit;
import com.company.models.Transaction;
import com.company.repositories.interfaces.IDepositRepository;
import com.company.repositories.interfaces.ITransactionRepository;

import java.util.List;

public class DepositController implements IDepositController {
    private final IDepositRepository repo;
    public DepositController(IDepositRepository repo) { // Dependency Injection
        this.repo = repo;
    }

    public String getAllMyDeposits(int id){
        List<Deposit> deps = repo.getAllMyDeposits(id);

        StringBuilder response = new StringBuilder();
        for (Deposit dep : deps) {
            response.append(dep.toString()).append("\n");
        }

        return response.toString();
    }

    public String getAllDeposits(){
        List<Deposit> deps = repo.getAllDeposits();

        StringBuilder response = new StringBuilder();
        for (Deposit dep : deps) {
            response.append(dep.toString()).append("\n");
        }

        return response.toString();
    }

    public String createDeposit(int userid, double percentage, int balance){
        Deposit deposit = new Deposit(userid, percentage, balance);

        int currentBalance = repo.checkBalanceUser(userid);

        if (currentBalance < balance){
            return "Insufficient funds";
        }

        boolean created = repo.createDeposit(deposit);

        return (created ? "Deposit created" : "An error occured");
    }

    public String replenishDeposit(int id, int userid, int amount){
        int currentBalanceU = repo.checkBalanceUser(userid);

        if (currentBalanceU < amount){
            return "Insufficient funds";
        }

        boolean replenished = repo.replenishDeposit(id, userid, amount);

        return (replenished ? "Replenished successfully" : "An error occured");
    }

    public String withdrawDeposit(int id, int userid, int amount){

        int currentBalanceD = repo.checkBalanceDeposit(id);

        if (currentBalanceD > amount){
            return "Insufficient funds in deposit";
        }

        boolean withdrawn = repo.withdrawDeposit(id, userid, amount);

        return (withdrawn ? "Withdrawn successfully" : "An error occured");
    }
}
