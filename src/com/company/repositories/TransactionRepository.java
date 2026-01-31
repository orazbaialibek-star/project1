package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.models.Transaction;
import com.company.repositories.interfaces.ITransactionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository implements ITransactionRepository {
    private final IDB db;

    public TransactionRepository(IDB db) {
        this.db = db;
    }

    public boolean userTransaction_admin(Transaction trans){
        Connection con = null;

        try {
            con = db.getConnection();

            if (trans.getAmount() <= 0) {
                return false;
            }

            if (trans.getUserTo() == 0 || trans.getUserFrom() == 0) {
                return false;
            }

            String sql1 = "INSERT INTO transactions (userfromid, usertoid, amount) VALUES (?, ?, ?)";
            PreparedStatement st1 = con.prepareStatement(sql1);

            st1.setInt(1, trans.getUserFrom());
            st1.setInt(2, trans.getUserTo());
            st1.setInt(3, trans.getAmount());

            st1.execute();

            String sql2 = "BEGIN; UPDATE users SET balance = balance - ? WHERE id = ?; UPDATE users SET balance = balance + ? WHERE id = ?; COMMIT;";
            PreparedStatement st2 = con.prepareStatement(sql2);

            st2.setInt(1,trans.getAmount());
            st2.setInt(2,trans.getUserFrom());
            st2.setInt(3, trans.getAmount());
            st2.setInt(4, trans.getUserTo());

            st2.execute();

            return true;
        } catch(SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }

    public List<Transaction> getAllTransactions(){
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id,userfromid,usertoid,amount FROM transactions";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Transaction> transes = new ArrayList<>();
            while (rs.next()) {
                Transaction trans = new Transaction(rs.getInt("id"),
                        rs.getInt("userfromid"),
                        rs.getInt("usertoid"),
                        rs.getInt("amount"));
                transes.add(trans);
            }

            return transes;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }
}
