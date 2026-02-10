package com.company.repositories;

import com.company.data.interfaces.IDB;
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

    public int id;

    public boolean authorisation(String login, String password){
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
