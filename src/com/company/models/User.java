package com.company.models;

public class User {
    private int id;
    private String name;
    private String surname;
    private double balance;
    private String login;
    private String password;

    public User(String name, String surname, double balance, String login, String password) {
        setName(name);
        setSurname(surname);
        setBank(balance);
        setLogin(login);
        setPassword(password);
    }

    public User(int id, String name, String surname, double balance, String login, String password) {
        this(name, surname, balance, login, password);
        setId(id);
    }

    public User(int id, String name, String surname, double balance) {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public double getBalance() {
        return balance;
    }
    public void setBank(double balance) {
        this.balance = balance;
    }
    public String getLogin(){
        return login;
    }
    public void setLogin(String login){
        this.login = login;
    }
    public String getPasword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String toString(){
        return "User{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", balance=" + getBalance() +
                '}';
    }
}
