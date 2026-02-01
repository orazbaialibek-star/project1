package com.company.models;

public class Deposit {
    private int id;
    private int userid;
    private double percentage;
    private int balance;

    public Deposit(int userid, double percentage, int balance){
        setUserid(userid);
        setPercentage(percentage);
        setBalance(balance);
    }

    public Deposit(int id, int userid, double percentage, int balance){
        this(userid, percentage, balance);
        setId(id);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
    public double getPercentage() {
        return percentage;
    }
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString(){
        return "Deposit{" +
                "id=" + getId() +
                ", user=" + getUserid() + '\'' +
                ", percentage='" + getPercentage() + '\'' +
                ", balance=" + getBalance() + '\'' +
                '}';
    }
}
