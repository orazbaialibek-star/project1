package com.company.models;

public class Authorisation {
    private String login;
    private int password;

    public Authorisation(String login, int password){
        setLogin(login);
        setPassword(password);
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
