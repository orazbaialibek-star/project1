package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.models.Deposit;
import com.company.models.Transaction;
import com.company.repositories.interfaces.IDepositRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepositRepository implements IDepositRepository {
    private final IDB db;

    public DepositRepository(IDB db) {
        this.db = db;
    }

    public List<Deposit> getAllMyDeposits(int id){
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id,userid,percentage,balance FROM deposits WHERE userid = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,id);

            ResultSet rs = st.executeQuery();

            List<Deposit> deps = new ArrayList<>();
            while (rs.next()) {
                Deposit dep = new Deposit(rs.getInt("id"),
                        rs.getInt("userid"),
                        rs.getDouble("percentage"),
                        rs.getInt("balance"));
                deps.add(dep);
            }

            return deps;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    public List<Deposit> getAllDeposits(){
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id,userid,percentage,balance FROM deposits";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            List<Deposit> deps = new ArrayList<>();
            while (rs.next()) {
                Deposit dep = new Deposit(rs.getInt("id"),
                        rs.getInt("userid"),
                        rs.getDouble("percentage"),
                        rs.getInt("balance"));
                deps.add(dep);
            }

            return deps;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    public boolean createDeposit(Deposit deposit){
        Connection con = null;

        try {
            con = db.getConnection();

            String sql1 = "INSERT INTO deposits (userid, percentage, balance) VALUES (?, ?, ?)";
            PreparedStatement st1 = con.prepareStatement(sql1);

            st1.setInt(1, deposit.getUserid());
            st1.setDouble(2, deposit.getPercentage());
            st1.setInt(3, deposit.getBalance());

            st1.execute();

            String sql2 = "BEGIN; UPDATE users SET balance = balance - ? WHERE id = ?; UPDATE deposits SET balance = balance + ? WHERE id = ?; COMMIT;";
            PreparedStatement st2 = con.prepareStatement(sql2);

            st2.setInt(1,deposit.getBalance());
            st2.setInt(2,deposit.getUserid());
            st2.setInt(3, deposit.getBalance());
            st2.setInt(4, deposit.getId());

            st2.execute();

            return true;
        } catch(SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }

    public boolean replenishDeposit(int id, int userid, int amount){
        Connection con = null;

        try {
            con = db.getConnection();

            String sql1 = "SELECT id,userid,percentage,balance FROM deposits WHERE id=? AND userid=?";
            PreparedStatement st1 = con.prepareStatement(sql1);

            st1.setInt(1, id);
            st1.setInt(2, userid);

            st1.execute();

            String sql2 = "BEGIN; UPDATE users SET balance = balance - ? WHERE id = ?; UPDATE deposits SET balance = balance + ? WHERE id = ?; COMMIT;";
            PreparedStatement st2 = con.prepareStatement(sql2);

            st2.setInt(1,amount);
            st2.setInt(2,userid);
            st2.setInt(3,amount);
            st2.setInt(4,id);

            st2.execute();

            return true;
        } catch(SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }

    public boolean withdrawDeposit(int id, int userid, int amount){
        Connection con = null;

        try {
            con = db.getConnection();

            String sql1 = "SELECT id,userid,percentage,balance FROM deposits WHERE id=? AND userid=?";
            PreparedStatement st1 = con.prepareStatement(sql1);

            st1.setInt(1, id);
            st1.setInt(2, userid);

            st1.execute();

            String sql2 = "BEGIN; UPDATE users SET balance = balance + ? WHERE id = ?; UPDATE deposits SET balance = balance - ? WHERE id = ?; COMMIT;";
            PreparedStatement st2 = con.prepareStatement(sql2);

            st2.setInt(1,amount);
            st2.setInt(2,userid);
            st2.setInt(3,amount);
            st2.setInt(4,id);

            st2.execute();

            return true;
        } catch(SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }

    public int checkBalanceUser(int userid){
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT balance FROM users WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,userid);

            ResultSet rs = st.executeQuery();

            if(rs.next()){
                return rs.getInt("balance");
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return 0;
    }

    public int checkBalanceDeposit(int id){
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT balance FROM deposits WHERE id=?";
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
}
