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

    @Override
    public List<User> getAllUsers() {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id,name,surname,balance FROM users";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getDouble("balance"));
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean createUser(User user) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO users(name,surname,balance) VALUES (?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, user.getName());
            st.setString(2, user.getSurname());
            st.setDouble(3, user.getBalance());

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public User getUser(int id){
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id,name,surname,balance FROM users WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,id);

            ResultSet rs = st.executeQuery();

            if (rs.next()){
                return new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }
    @Override
    public boolean deleteUser(int id) {
        Connection con = null;

        try {
            con = db.getConnection();

            String checkSql = "SELECT id FROM users WHERE id = ?";
            PreparedStatement checkSt = con.prepareStatement(checkSql);
            checkSt.setInt(1, id);
            ResultSet rs = checkSt.executeQuery();

            if (!rs.next()) {
                System.out.println("User with ID " + id + " not found!");
                return false;
            }

            String deleteSql = "DELETE FROM users WHERE id = ?";
            PreparedStatement deleteSt = con.prepareStatement(deleteSql);
            deleteSt.setInt(1, id);

            int rowsDeleted = deleteSt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println(" User with ID " + id + " deleted successfully!");
                System.out.println("Note: All transactions related to this user were also deleted (CASCADE).");
                return true;
            } else {
                System.out.println("Failed to delete user with ID " + id);
                return false;
            }

        } catch (SQLException e) {
            System.out.println("SQL error in deleteUser: " + e.getMessage());
            return false;
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Connection close error: " + e.getMessage());
            }
        }
    }
}
