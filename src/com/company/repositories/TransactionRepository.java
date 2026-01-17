package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.models.Transaction;
import com.company.models.User;
import com.company.repositories.interfaces.ITransactionRepository;
import com.company.repositories.UserRepository;

import java.sql.*;

public class TransactionRepository implements ITransactionRepository {
    private final IDB db;  // Dependency Injection

    public TransactionRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean userTransaction(Transaction trans){
        Connection con = null;

        try {
            con = db.getConnection();
            String sql1 = "INSERT INTO transactions (userfromid, usertoid, amount) VALUES (?, ?, ?)";
            PreparedStatement st1 = con.prepareStatement(sql1);

            st1.setInt(1, trans.getUserFrom());
            st1.setInt(2, trans.getUserTo());
            st1.setInt(3, trans.getAmount());

            st1.execute();

            String sql2 = "UPDATE users SET balance = balance - ? WHERE id = ?";
            PreparedStatement st2 = con.prepareStatement(sql2);

            st2.setInt(1,trans.getAmount());
            st2.setInt(2,trans.getUserFrom());

            st2.execute();

            String sql3 = "UPDATE users SET balance = balance + ? WHERE id = ?";
            PreparedStatement st3 = con.prepareStatement(sql3);
            st3.setInt(1, trans.getAmount());
            st3.setInt(2, trans.getUserTo());
            st3.execute();

            return true;
        } catch(SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return false;
    }
}
