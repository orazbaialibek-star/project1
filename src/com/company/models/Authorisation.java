package com.company.models;

public class Authorisation {
    private String login;
    private String password;

    public Authorisation(String login, String password){
        setLogin(login);
        setPassword(password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
