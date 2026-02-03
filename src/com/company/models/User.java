package com.company.models;

public class User {
    private int id;
    private String name;
    private String surname;
    private double balance;
    private String login;
    private String password;
    private int role;

    private User(UserBuilder builder){
        id = builder.id;
        name = builder.name;
        surname = builder.surname;
        balance = builder.balance;
        login = builder.login;
        password = builder.password;
        role = builder.role;
    }

    public static class UserBuilder{
        private int id;
        private String name;
        private String surname;
        private double balance;
        private String login;
        private String password;
        private int role;

        public UserBuilder(String name, String surname) {
            this.name=name;
            this.surname=surname;
        }

        public UserBuilder(int id, String name, String surname){
            this(name, surname);
            this.id=id;
        }

        public UserBuilder WithRole(int role){
            this.role=role;
            return this;
        }
        public UserBuilder WithBalance(double balance){
            this.balance=balance;
            return this;
        }
        public UserBuilder WithLogin(String login){
            this.login=login;
            return this;
        }
        public UserBuilder WithPassword(String password){
            this.password=password;
            return this;
        }
        public User build(){
            return new User(this);
        }
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
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public int getRole(){
        return role;
    }
    public void setRole(int role){
        this.role = role;
    }

    public String getLogin(){
        return login;
    }
    public void setLogin(String login){
        this.login = login;
    }
    public String getPassword(){
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
                ", balance=" + getBalance() + '\'' +
                ", login=" + getLogin() + '\'' +
                ", password=" + getPassword() + '\'' +
                ", role=" + getRole() + '\'' +
                '}';
    }
}
