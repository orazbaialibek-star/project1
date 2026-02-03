package com.company.models;

public class Transaction {
    private int id;
    private int userFrom;
    private int userTo;
    private int amount;
    private int depositTo;

    public Transaction(int userFrom, int userTo, int amount){
        setUserFrom(userFrom);
        setUserTo(userTo);
        setAmount(amount);
    }

    public Transaction(int id, int userFrom, int userTo, int amount){
        this(userFrom, userTo, amount);
        setId(id);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserFrom() {
        return userFrom;
    }
    public void setUserFrom(int userFrom) {
        this.userFrom = userFrom;
    }
    public int getUserTo() {
        return userTo;
    }
    public void setUserTo(int userTo) {
        this.userTo = userTo;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getDepositTo() {
        return depositTo;
    }
    public void setDepositTo(int depositTo) {
        this.depositTo = depositTo;
    }

    @Override
    public String toString(){
        return "Transaction{" +
                "id=" + getId() +
                ", from user='" + getUserFrom() + '\'' +
                ", to user='" + getUserTo() + '\'' +
                ", with sent amount of=" + getAmount() +
                '}';
    }
}

