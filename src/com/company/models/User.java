package com.company.models;

public class User {
    private int id;
    private String name;
    private String surname;
    private double bank;

    public User(String name, String surname, double bank) {
        setName(name);
        setSurname(surname);
        setBank(bank);
    }

    public User(int id, String name, String surname, double bank) {
        this(name, surname, bank);
        setId(id);
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
    public double getBank() {
        return bank;
    }
    public void setBank(double bank) {
        this.bank = bank;
    }

    @Override
    public String toString(){
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender=" + bank +
                '}';
    }
}
