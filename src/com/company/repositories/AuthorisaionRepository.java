package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.models.Authorisation;
import com.company.models.User;
import com.company.repositories.interfaces.IAuthorisationRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorisaionRepository implements IAuthorisationRepository{
    private final IDB db;

    public AuthorisaionRepository(IDB db) {
        this.db = db;
    }

    public int role;

    public String login;

    public int id;

    public int checkId(String login){
        Connection con = null;

        try {

            con = db.getConnection();
            String sql1 = "SELECT id FROM users WHERE login=?";
            PreparedStatement st1 = con.prepareStatement(sql1);

            st1.setString(1,login);

            ResultSet rs1 = st1.executeQuery();

            if(rs1.next()){
                id = rs1.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
            return 0;
        }
        return id;
    }

    public int checkRole(String login){
        Connection con = null;

        try {

            con = db.getConnection();
            String sql1 = "SELECT role FROM users WHERE login=?";
            PreparedStatement st1 = con.prepareStatement(sql1);

            st1.setString(1,login);

            ResultSet rs1 = st1.executeQuery();

            if(rs1.next()){
                role = rs1.getInt("role");
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
            return 0;
        }
        return role;
    }

    public String checkLogin(String login){
        Connection con = null;

        try {

            con = db.getConnection();
            String sql1 = "SELECT login FROM users WHERE login=?";
            PreparedStatement st1 = con.prepareStatement(sql1);

            st1.setString(1,login);

            ResultSet rs1 = st1.executeQuery();

            if(rs1.next()){
                login = rs1.getString("login");
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
            return null;
        }
        return login;
    }

    public boolean checkLogPas(String login, String password){
        Connection con = null;

        try {

            con = db.getConnection();
            String sql1 = "SELECT login FROM users WHERE login=?";
            PreparedStatement st1 = con.prepareStatement(sql1);

            st1.setString(1,login);

            ResultSet rs1 = st1.executeQuery();

            String sql2 = "SELECT password FROM users WHERE password=?";
            PreparedStatement st2 = con.prepareStatement(sql2);

            st2.setString(1,password);

            ResultSet rs2 = st2.executeQuery();

            if (rs1.next() && rs2.next()){
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
            return false;
        }
    }
}
