package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.models.User;
import com.company.repositories.interfaces.IUserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDB db;  // Dependency Injection

    public UserRepository(IDB db) {
        this.db = db;
    }

    public int role;

    public String name;

    public String surname;

    public String login;

    public int id;

    public List<User> getAllUsers() {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id,name,surname,balance,login,password,role FROM users";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User.
                        UserBuilder(rs.getInt("id"), rs.getString("name"), rs.getString("surname"))
                        .WithBalance(rs.getInt("balance"))
                        .WithLogin(rs.getString("login"))
                        .build();
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    public boolean createUser(User user) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO users(name,surname,balance,login,password,role) VALUES (?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, user.getName());
            st.setString(2, user.getSurname());
            st.setDouble(3, user.getBalance());
            st.setString(4, user.getLogin());
            st.setString(5, user.getPassword());
            st.setInt(6,user.getRole());

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return false;
    }

    public User getUser(int id){
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id,name,surname,balance,login,password,role FROM users WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,id);

            ResultSet rs = st.executeQuery();

            if (rs.next()){
                return new User.
                        UserBuilder(rs.getInt("id"), rs.getString("name"), rs.getString("surname"))
                        .WithBalance(rs.getInt("balance"))
                        .WithLogin(rs.getString("login"))
                        .WithPassword(rs.getString("password"))
                        .WithRole(rs.getInt("role"))
                        .build();
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    public boolean deleteUser(int id) {
        Connection con = null;

        try {
            con = db.getConnection();

            String deleteSql = "DELETE FROM users WHERE id = ?";
            PreparedStatement st = con.prepareStatement(deleteSql);
            st.setInt(1, id);

            int rows = st.executeUpdate();

            if (rows > 0){
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("SQL error in deleteUser: " + e.getMessage());
            return false;
        }
    }

    public List<String> getUsersWithTransactions() {
        Connection con = null;

        try {
            con = db.getConnection();

            String sql =
                    "SELECT u.name, u.surname, t.amount "+
                            "FROM users u " +
                            "JOIN transactions t ON u.id = t.userfromid";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            List<String> result = new ArrayList<>();

            while (rs.next()) {
                result.add(
                        "User: " + rs.getString("name") + " " +
                                rs.getString("surname") +
                                ", amount: " + rs.getDouble("amount")
                );
            }
            return result;

        } catch (SQLException e) {
            System.out.println("SQL error (JOIN): " + e.getMessage());
        }

        return new ArrayList<>();
    }

    public int checkBalance(int id){
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT balance FROM users WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,id);

            ResultSet rs = st.executeQuery();

            if(rs.next()){
                return rs.getInt("balance");
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return 0;
    }
    public int getId(String login){
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

    public int getRole(String login){
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

    public String getFullName(String login){
        Connection con = null;

        try {

            con = db.getConnection();
            String sql1 = "SELECT name, surname FROM users WHERE login=?";
            PreparedStatement st1 = con.prepareStatement(sql1);

            st1.setString(1,login);

            ResultSet rs1 = st1.executeQuery();

            if(rs1.next()){
                name = rs1.getString("name");
                surname = rs1.getString("surname");
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
            return null;
        }
        return name + " " + surname;
    }

    public String getLogin(String login){
        Connection con = null;

        try {

            con = db.getConnection();
            String sql1 = "SELECT login FROM users WHERE login=?";
            PreparedStatement st1 = con.prepareStatement(sql1);

            st1.setString(1,login);

            ResultSet rs1 = st1.executeQuery();

            if(rs1.next()){
                login = rs1.getString("login");
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
            return null;
        }
        return login;
    }
}

